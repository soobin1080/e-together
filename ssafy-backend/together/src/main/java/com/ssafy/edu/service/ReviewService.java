package com.ssafy.edu.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.edu.dao.ReviewDaoImpl;
import com.ssafy.edu.model.Review;
import com.ssafy.edu.model.ReviewCount;
import com.ssafy.edu.model.ReviewFile;
import com.ssafy.edu.model.ReviewResult;

@Service
public class ReviewService implements IReviewService {

	@Autowired
	private ReviewDaoImpl reviewdao;

	@Override
	public List<ReviewResult> getAllReview() {
		// TODO Auto-generated method stub
		return reviewdao.getAllReview();
	}

	@Override
	public void insertReview(Review review) {
		// TODO Auto-generated method stub
		 reviewdao.insertReview(review);
	}

	@Override
	public void insertReviewFile(ReviewFile file) {
		// TODO Auto-generated method stub
		reviewdao.insertReviewFile(file);
	}
	
	@Override
	public void updateReview(Review review) {
		// TODO Auto-generated method stub
		reviewdao.updateReview(review);
	}

	@Override
	public void deleteReview(int review_num) {
		// TODO Auto-generated method stub
		reviewdao.deleteReview(review_num);
	}

	@Override
	public Review getOneReview(int review_num) {
		// TODO Auto-generated method stub
		return reviewdao.getOneReview(review_num);
	}

	@Override
	public void insertReviewLikeCount(ReviewCount reviewcount) {
		// TODO Auto-generated method stub
		reviewdao.insertReviewLikeCount(reviewcount);
	}

	@Override
	public void updateReviewLikeCount(int review_num) {
		// TODO Auto-generated method stub
		reviewdao.updateReviewLikeCount(review_num);
	}

	@Override
	public int getLastReviewNumber(int budget_num) {
		// TODO Auto-generated method stub
		return reviewdao.getLastReviewNumber(budget_num);
	}

	@Override
	public List<String> getReviewLikeUser(int review_num) {
		// TODO Auto-generated method stub
		return reviewdao.getReviewLikeUser(review_num);
	}

	@Override
	public void deleteReviewLikeCount(ReviewCount reviewcount) {
		// TODO Auto-generated method stub
		reviewdao.deleteReviewLikeCount(reviewcount);
	}

	@Override
	public String getReviewImage(int review_num) {
		// TODO Auto-generated method stub
		return reviewdao.getReviewImage(review_num);
	}

}
