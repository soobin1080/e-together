package com.ssafy.edu.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.edu.model.BudgetInfo;
import com.ssafy.edu.model.BudgetListResult;
import com.ssafy.edu.model.Review;
import com.ssafy.edu.model.ReviewCount;
import com.ssafy.edu.model.ReviewDetailResult;
import com.ssafy.edu.model.ReviewFile;
import com.ssafy.edu.model.ReviewPage;
import com.ssafy.edu.model.ReviewResult;
import com.ssafy.edu.service.IBudgetService;
import com.ssafy.edu.service.IReviewService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

//http://localhost:8197/together/swagger-ui.html
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api")
@Api(value = "SSAFY", description = "SSAFY 2020")
public class ReviewController {
	public static final Logger logger = LoggerFactory.getLogger(ReviewController.class);

	@Autowired
	private IReviewService reviewservice;

	@Autowired
	private IBudgetService budgetservice;

	int recentPage = 0;

	@ApiOperation(value = "전체 review 뿌려주기", response = ReviewPage.class)
	@RequestMapping(value = "/review", method = RequestMethod.GET)
	public ResponseEntity<ReviewPage> getAllReview(@RequestParam(required = false, defaultValue = "1") int page)
			throws Exception {
		logger.info("1-------------getAllReview-----------------------------" + new Date());

		recentPage = page;

		ReviewPage reviewpage = reviewservice.getReviewListWithPage(page);

		if (reviewpage == null) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<ReviewPage>(reviewpage, HttpStatus.OK);
	}

	@ApiOperation(value = "예산,인원에 맞는 review 뿌려주기", response = ReviewPage.class)
	@RequestMapping(value = "/review/{personnel}/{budget}", method = RequestMethod.GET)
	public ResponseEntity<ReviewPage> getWantedReview(@RequestParam(required = false, defaultValue = "1") int page,
			@PathVariable int personnel, @PathVariable int budget) throws Exception {
		logger.info("1-------------getWantedReview-----------------------------" + new Date());

		recentPage = page;

		ReviewPage reviewpage = reviewservice.getWantedReviewListWithPage(page, personnel, budget);

		if (reviewpage == null) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<ReviewPage>(reviewpage, HttpStatus.OK);
	}

	@ApiOperation(value = "review 상세보기", response = ReviewResult.class)
	@RequestMapping(value = "/review/{review_num}", method = RequestMethod.GET)
	public ResponseEntity<ReviewDetailResult> getOneReview(@PathVariable int review_num) throws Exception {
		logger.info("2-------------getOneReview-----------------------------" + new Date());

		ReviewDetailResult reviewdetailresult = new ReviewDetailResult();

		// name, personnel, like_count 도 반환하도록 고치자.
		Review review = reviewservice.getOneReview(review_num);
		int budget_num = review.getBudget_num();
		BudgetInfo budgetinfo = budgetservice.getOneBudgetInfo(budget_num);
		List<BudgetListResult> budgetlist = budgetservice.getOneBudgetList(budget_num);
		List<String> like_user = reviewservice.getReviewLikeUser(review_num);
		String review_img = reviewservice.getReviewImage(review_num);

		reviewdetailresult.setReview(review);
		reviewdetailresult.setBudgetinfo(budgetinfo);
		reviewdetailresult.setBudgetlist(budgetlist);
		reviewdetailresult.setLike_user(like_user);
		reviewdetailresult.setReview_img(review_img);

		return new ResponseEntity<ReviewDetailResult>(reviewdetailresult, HttpStatus.OK);
	}

	@ApiOperation(value = "review 작성하기", response = ReviewResult.class)
	@RequestMapping(path = "/review", method = RequestMethod.POST)
	public String uploadImage(HttpServletRequest httpServletRequest, @RequestPart MultipartFile files)
			throws IOException {
		logger.info("3-------------uploadImage-----------------------------" + new Date());

		Review review = new Review();
		review.setBudget_num(Integer.parseInt(httpServletRequest.getParameter("budget_num")));
		review.setReview_content(httpServletRequest.getParameter("review_content"));
		reviewservice.insertReview(review);

		int review_num = reviewservice.getLastReviewNumber(review.getBudget_num());

		System.out.println("======================");
		System.out.println(files.getName());
		System.out.println(files.getOriginalFilename());
		System.out.println(files.getContentType());
		System.out.println("======================");

		String path = "/home/ubuntu/opt/assets/";
		String fileName = files.getOriginalFilename();
		byte[] imageData = files.getBytes();

		FileOutputStream fileOutputStream = null;
		String url = "";
		try {
			fileName = RandomStringUtils.randomAlphanumeric(32) + "." + fileName;
			url = path + fileName;
			File newfile = new File(url);
			fileOutputStream = new FileOutputStream(newfile);
			fileOutputStream.write(imageData);
		} catch (Throwable e) {
			e.printStackTrace(System.out);
		} finally {
			fileOutputStream.close();
			ReviewFile file = new ReviewFile();
			file.setReview_num(review_num);
			file.setFile_name(fileName);
			file.setFile_ori_name(files.getOriginalFilename());
			file.setFile_url("/together/assets/" + fileName);

			reviewservice.insertReviewFile(file); // 게시글 이미지 파일 insert
		}
		return url;
	}

	@ApiOperation(value = "review 수정하기", response = ReviewResult.class)
	@RequestMapping(value = "/review/{review_num}", method = RequestMethod.POST)
	public void updateReview(@RequestBody Review review) throws Exception {
		logger.info("4-------------updateReview-----------------------------" + new Date());
		reviewservice.updateReview(review);
	}

	@ApiOperation(value = "review 좋아요 수 update", response = ReviewCount.class)
	@RequestMapping(value = "/review/like_count", method = RequestMethod.POST)
	public void insertReviewLikeCount(@RequestBody ReviewCount reviewcount) throws Exception {
		logger.info("5-------------insertReviewLikeCount-----------------------------" + new Date());

		System.out.println("좋아요 수 찾기" + reviewcount);
		reviewservice.insertReviewLikeCount(reviewcount);
		reviewservice.updateReviewLikeCount(reviewcount.getReview_num());
	}

	@ApiOperation(value = "review 좋아요 취소", response = ReviewCount.class)
	@RequestMapping(value = "/review/like_count/{review_num}/{user_email}", method = RequestMethod.DELETE)
	public void deleteReviewLikeCount(@PathVariable int review_num, @PathVariable String user_email) throws Exception {
		logger.info("6-------------deleteReviewLikeCount-----------------------------" + new Date());
		ReviewCount reviewcount = new ReviewCount();
		reviewcount.setReview_num(review_num);
		reviewcount.setUser_email(user_email);
		System.out.println("좋아요 취소 : " + reviewcount);

		reviewservice.deleteReviewLikeCount(reviewcount);
		reviewservice.updateReviewLikeCount(reviewcount.getReview_num());
	}

	@ApiOperation(value = "review 삭제하기", response = ReviewResult.class)
	@RequestMapping(value = "/review/{review_num}", method = RequestMethod.DELETE)
	public ResponseEntity<ReviewResult> deleteReview(@PathVariable int review_num) throws Exception {
		logger.info("7-------------deleteReview-----------------------------" + new Date());

		reviewservice.deleteReview(review_num);

		return new ResponseEntity<ReviewResult>(HttpStatus.OK);
	}

}
