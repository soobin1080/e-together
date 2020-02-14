package com.ssafy.edu.controller;

import java.util.Date;
import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.edu.model.BudgetInfo;
import com.ssafy.edu.model.BudgetListResult;
import com.ssafy.edu.model.Review;
import com.ssafy.edu.model.ReviewDetailResult;
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

	@ApiOperation(value = "전체 review 뿌려주기", response = List.class)
	@RequestMapping(value = "/review", method = RequestMethod.GET)
	public ResponseEntity<List<ReviewResult>> getAllReview() throws Exception {
		logger.info("1-------------getAllReview-----------------------------" + new Date());

		// name, personnel, like_count 도 반환하도록 고치자.
		List<ReviewResult> reviewlist = reviewservice.getAllReview();
		System.out.println(" 된다 : " + reviewlist);
		return new ResponseEntity<List<ReviewResult>>(reviewlist, HttpStatus.OK);
	}

	@ApiOperation(value = "review 상세보기", response = ReviewResult.class)
	@RequestMapping(value = "/review/{review_num}", method = RequestMethod.GET)
	public ResponseEntity<ReviewDetailResult> getOneReview(@PathVariable int review_num) throws Exception {
		logger.info("2-------------getOneReview-----------------------------" + new Date());

		ReviewDetailResult reviewdetailresult = new ReviewDetailResult();

		// name, personnel, like_count 도 반환하도록 고치자.
		Review review = reviewservice.getOneReview(review_num);
		int budget_num = review.getBudget_num();
		BudgetInfo budgetinfo=budgetservice.getOneBudgetInfo(budget_num);
		List<BudgetListResult> budgetlist = budgetservice.getOneBudgetList(budget_num);
		

		reviewdetailresult.setReview(review);
		reviewdetailresult.setBudgetinfo(budgetinfo);
		reviewdetailresult.setBudgetlist(budgetlist);
		
		return new ResponseEntity<ReviewDetailResult>(reviewdetailresult, HttpStatus.OK);
	}

	@ApiOperation(value = "review 작성하기", response = ReviewResult.class)
	@RequestMapping(value = "/review", method = RequestMethod.POST)
	public ResponseEntity<Review> insertReview(@RequestBody Review review) throws Exception {
		logger.info("3-------------insertReview-----------------------------" + new Date());

		reviewservice.insertReview(review);

		return new ResponseEntity<Review>(review, HttpStatus.OK);
	}

	@ApiOperation(value = "review 수정하기", response = ReviewResult.class)
	@RequestMapping(value = "/review/{review_num}", method = RequestMethod.POST)
	public void updateReview(@RequestBody Review review) throws Exception {
		logger.info("4-------------updateReview-----------------------------" + new Date());

		reviewservice.updateReview(review);

	}

	@ApiOperation(value = "review 삭제하기", response = ReviewResult.class)
	@RequestMapping(value = "/review/{review_num}", method = RequestMethod.DELETE)
	public ResponseEntity<ReviewResult> deleteReview(@PathVariable int review_num) throws Exception {
		logger.info("5-------------deleteReview-----------------------------" + new Date());

		reviewservice.deleteReview(review_num);

		return new ResponseEntity<ReviewResult>(HttpStatus.OK);
	}

}
