package com.ssafy.edu.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.edu.model.ReviewFile;
import com.ssafy.edu.model.Review;
import com.ssafy.edu.model.ReviewCount;
import com.ssafy.edu.model.ReviewResult;

@Repository
public class ReviewDaoImpl {
	String ns = "ssafy.review.";

	@Autowired
	private SqlSession sqlSession;

	public List<ReviewResult> getAllReview(int startContent) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(ns + "getAllReview",startContent);
	}
	
	public Review getOneReview(int review_num) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(ns + "getOneReview",review_num);
	}

	//후기 작성하기
	public void insertReview(Review review) {
		// TODO Auto-generated method stub
		sqlSession.insert(ns + "insertReview", review);
	}
	
	public void insertReviewFile(ReviewFile file) {
		// TODO Auto-generated method stub
		sqlSession.insert(ns+"insertReviewFile",file);
	}

	//후기 수정하기
	public void updateReview(Review review) {
		// TODO Auto-generated method stub
		sqlSession.update(ns + "updateReview", review);
	}

	//후기 삭제하기
	public void deleteReview(int review_num) {
		// TODO Auto-generated method stub
		sqlSession.delete(ns + "deleteReview", review_num);
	}

	public void insertReviewLikeCount(ReviewCount reviewcount) {
		// TODO Auto-generated method stub
		sqlSession.insert(ns+"insertReviewLikeCount", reviewcount);
		
	}

	public void updateReviewLikeCount(int review_num) {
		// TODO Auto-generated method stub
		sqlSession.insert(ns+"updateReviewLikeCount", review_num);
	}

	public int getLastReviewNumber(int budget_num) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(ns+"getLastReviewNumber",budget_num);
	}

	public List<String> getReviewLikeUser(int review_num) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(ns+"getReviewLikeUser",review_num);
	}

	public void deleteReviewLikeCount(ReviewCount reviewcount) {
		// TODO Auto-generated method stub
		sqlSession.delete(ns+"deleteReviewLikeCount",reviewcount);
	}

	public String getReviewImage(int review_num) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(ns+"getReviewImage",review_num);
	}

	public List<ReviewResult> getWantedReview(int startContent,int personnel, int budget) {
		// TODO Auto-generated method stub
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("startContent", startContent);
		paramMap.put("personnel", personnel);
		paramMap.put("budget", budget);
		return sqlSession.selectList(ns+"getWantedReview",paramMap);
	}

	public int cntTotReview() {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(ns + "cntTotReview");
	}

	public int cntTotWantedReview(int personnel, int budget) {
		// TODO Auto-generated method stub
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("personnel", personnel);
		paramMap.put("budget", budget);
		return sqlSession.selectOne(ns + "cntTotWantedReview",paramMap);
	}

}
