package com.ssafy.edu.service;

import java.util.List;

import com.ssafy.edu.model.Review;
import com.ssafy.edu.model.ReviewCount;
import com.ssafy.edu.model.ReviewFile;
import com.ssafy.edu.model.ReviewResult;

public interface IReviewService {

	List<ReviewResult> getAllReview();

	void insertReview(Review review);

	void updateReview(Review review);

	void deleteReview(int review_num);

	Review getOneReview(int review_num);

	void insertReviewFile(ReviewFile file);

	void insertReviewLikeCount(ReviewCount reviewcount);

	void updateReviewLikeCount(int review_num);

	int getLastReviewNumber(int budget_num);

	List<String> getReviewLikeUser(int review_num);

	void deleteReviewLikeCount(ReviewCount reviewcount);
}
