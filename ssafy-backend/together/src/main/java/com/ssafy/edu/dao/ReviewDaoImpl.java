package com.ssafy.edu.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.edu.model.Review;
import com.ssafy.edu.model.ReviewResult;

@Repository
public class ReviewDaoImpl {
	String ns = "ssafy.review.";

	@Autowired
	private SqlSession sqlSession;

	public List<ReviewResult> getAllReview() {
		// TODO Auto-generated method stub
		return sqlSession.selectList(ns + "getAllReview");
	}
	
	public Review getOneReview(int review_num) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(ns + "getOneReview",review_num);
	}

	public void insertReview(Review review) {
		// TODO Auto-generated method stub
		sqlSession.insert(ns + "insertReview", review);
	}

	public void updateReview(Review review) {
		// TODO Auto-generated method stub
		sqlSession.update(ns + "updateReview", review);
	}

	public void deleteReview(int review_num) {
		// TODO Auto-generated method stub
		sqlSession.delete(ns + "deleteReview", review_num);
	}

	

}
