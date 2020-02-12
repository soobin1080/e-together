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

import com.ssafy.edu.model.File;
import com.ssafy.edu.model.Review;
import com.ssafy.edu.model.ReviewResult;
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

	@ApiOperation(value = "전체 review 뿌려주기", response = List.class)
	@RequestMapping(value = "/review", method = RequestMethod.GET)
	public ResponseEntity<List<ReviewResult>> getAllReview() throws Exception {
		logger.info("1-------------getAllReview-----------------------------" + new Date());

		// name, personnel, like_count 도 반환하도록 고치자.

		List<ReviewResult> reviewlist = reviewservice.getAllReview();
		System.out.println(" 된다 : "+reviewlist);
		return new ResponseEntity<List<ReviewResult>>(reviewlist, HttpStatus.OK);
	}

//	@ApiOperation(value = "review 상세보기", response = ReviewResult.class)
//	@RequestMapping(value = "/review/{user_email}/{budget_title}", method = RequestMethod.GET)
//	public ResponseEntity<ReviewResult> getOneReview(@RequestBody Review review) throws Exception {
//		logger.info("2-------------getOneReview-----------------------------" + new Date());
//
//		// name, personnel, like_count 도 반환하도록 고치자.
//		ReviewResult onereview = reviewservice.getOneReview(review.getUser_email(), review.getBudget_title());
//
//		return new ResponseEntity<ReviewResult>(onereview, HttpStatus.OK);
//	}

	@ApiOperation(value = "review 작성하기", response = ReviewResult.class)
	@RequestMapping(value = "/review", method = RequestMethod.POST)
	public ResponseEntity<ReviewResult> insertReview(@RequestBody ReviewResult reviewresult) throws Exception {
		logger.info("3-------------insertReview-----------------------------" + new Date());

		reviewservice.insertReview(reviewresult);

		return new ResponseEntity<ReviewResult>(reviewresult, HttpStatus.OK);
	}

	@ApiOperation(value = "review 수정하기", response = ReviewResult.class)
	@RequestMapping(value = "/review/update", method = RequestMethod.POST)
	public ResponseEntity<ReviewResult> updateReview(@RequestBody ReviewResult reviewresult) throws Exception {
		logger.info("4-------------updateReview-----------------------------" + new Date());

		reviewservice.updateReview(reviewresult);

		return new ResponseEntity<ReviewResult>(reviewresult, HttpStatus.OK);
	}

	@ApiOperation(value = "review 삭제하기", response = ReviewResult.class)
	@RequestMapping(value = "/review", method = RequestMethod.DELETE)
	public ResponseEntity<ReviewResult> deleteReview(@RequestBody ReviewResult reviewresult) throws Exception {
		logger.info("5-------------deleteReview-----------------------------" + new Date());

		reviewservice.deleteReview(reviewresult.getUser_email(), reviewresult.getBudget_title());

		return new ResponseEntity<ReviewResult>(reviewresult, HttpStatus.OK);
	}

}
