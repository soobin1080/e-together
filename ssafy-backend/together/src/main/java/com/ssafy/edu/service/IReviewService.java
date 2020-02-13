package com.ssafy.edu.service;

import java.util.List;

import com.ssafy.edu.model.Review;
import com.ssafy.edu.model.ReviewResult;

public interface IReviewService {

	List<ReviewResult> getAllReview();

	void insertReview(Review review);

	void updateReview(Review review);

	void deleteReview(int review_num);

	Review getOneReview(int review_num);
}
