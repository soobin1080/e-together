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

import com.ssafy.edu.model.Reply;
import com.ssafy.edu.model.ReviewResult;
import com.ssafy.edu.service.IReplyService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

//http://localhost:8197/together/swagger-ui.html
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api")
@Api(value = "SSAFY", description = "SSAFY 2020")
public class ReplyController {
	public static final Logger logger = LoggerFactory.getLogger(ReplyController.class);

	@Autowired
	private IReplyService replyservice;
	
	@ApiOperation(value = "후기 전체 댓글 뿌리주기", response = List.class)
	@RequestMapping(value = "/reply/{review_num}", method = RequestMethod.GET)
	public ResponseEntity<List<Reply>> getAllReply(@PathVariable int review_num) throws Exception {
		logger.info("1-------------getAllReview-----------------------------" + new Date());

		List<Reply> reviewlist = replyservice.getAllReply(review_num);

		return new ResponseEntity<List<Reply>>(HttpStatus.OK);
	}
	
	@ApiOperation(value = "댓글 작성하기", response = Reply.class)
	@RequestMapping(value = "/reply", method = RequestMethod.POST)
	public ResponseEntity<Reply> insertReply(@RequestBody Reply reply) throws Exception {
		logger.info("2-------------insertReply-----------------------------" + new Date());

		replyservice.insertReply(reply);

		return new ResponseEntity<Reply>(reply, HttpStatus.OK);
	}
	
	@ApiOperation(value = "댓글 삭제하기", response = Reply.class)
	@RequestMapping(value = "/reply/{review_num}", method = RequestMethod.DELETE)
	public ResponseEntity<Reply> deleteReply(@PathVariable int review_num) throws Exception {
		logger.info("3-------------deleteReply-----------------------------" + new Date());

//		replyservice.deleteReply(review_num);

		return new ResponseEntity<Reply>(HttpStatus.OK);
	}
	
	@ApiOperation(value = "댓글 수정하기", response = Reply.class)
	@RequestMapping(value = "/reply/{review_num}/{wrtier_email}", method = RequestMethod.POST)
	public ResponseEntity<Reply> updateReply(@RequestBody Reply reply) throws Exception {
		logger.info("4-------------updateReply-----------------------------" + new Date());

		replyservice.updateReply(reply);

		return new ResponseEntity<Reply>(reply, HttpStatus.OK);
	}

	
}
