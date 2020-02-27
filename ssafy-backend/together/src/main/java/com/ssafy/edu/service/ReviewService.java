package com.ssafy.edu.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.edu.dao.ReviewDaoImpl;
import com.ssafy.edu.model.ProductPage;
import com.ssafy.edu.model.Review;
import com.ssafy.edu.model.ReviewCount;
import com.ssafy.edu.model.ReviewFile;
import com.ssafy.edu.model.ReviewPage;
import com.ssafy.edu.model.ReviewResult;

@Service
public class ReviewService implements IReviewService {

	@Autowired
	private ReviewDaoImpl reviewdao;

	@Override
	public List<ReviewResult> getAllReview(int startContent) {
		// TODO Auto-generated method stub
		return reviewdao.getAllReview(startContent);
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

	@Override
	public List<ReviewResult> getWantedReview(int startContent,int personnel, int budget) {
		// TODO Auto-generated method stub
		return reviewdao.getWantedReview(startContent,personnel, budget);
	}

	@Override
	public ReviewPage getReviewListWithPage(int nowPage) {
		// TODO Auto-generated method stub
		int totPage = (int) Math.ceil(reviewdao.cntTotReview() / 6.0);
		System.out.println("totPage : " + totPage);
		System.out.println("nowPage : " + nowPage);

		int startContent = (nowPage - 1) * 6;
		System.out.println("startContent : " + startContent);

		// name, personnel, like_count 도 반환
		List<ReviewResult> reviewlist = reviewdao.getAllReview(startContent);

		for (int i = 0; i < reviewlist.size(); i++) {
			List<String> reviewlikeuser = reviewdao.getReviewLikeUser(reviewlist.get(i).getReview_num());
			reviewlist.get(i).setLike_user(reviewlikeuser);
		}

		return new ReviewPage(reviewlist, nowPage, 1, totPage, totPage);
	}

	@Override
	public ReviewPage getWantedReviewListWithPage(int nowPage, int personnel, int budget) {
		// TODO Auto-generated method stub
		int totPage = (int) Math.ceil(reviewdao.cntTotWantedReview(personnel,budget) / 6.0);
		System.out.println("totPage : " + totPage);
		System.out.println("nowPage : " + nowPage);

		int startContent = (nowPage - 1) * 6;
		System.out.println("startContent : " + startContent);
		// name, personnel, like_count 도 반환
		List<ReviewResult> reviewlist = reviewdao.getWantedReview(startContent,personnel, budget);

		for (int i = 0; i < reviewlist.size(); i++) {
			List<String> reviewlikeuser = reviewdao.getReviewLikeUser(reviewlist.get(i).getReview_num());
			reviewlist.get(i).setLike_user(reviewlikeuser);
		}

		return new ReviewPage(reviewlist, nowPage, 1, totPage, totPage);
	}

}
