package com.ssafy.edu.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.edu.dao.ReviewDaoImpl;
import com.ssafy.edu.model.Review;
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

}
