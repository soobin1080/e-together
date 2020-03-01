package com.ssafy.edu.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.edu.model.RecommendResult;

@Repository
public class RecommendDaoImpl {

	String ns = "ssafy.recommend.";

	@Autowired
	private SqlSession sqlSession;

	public List<Integer> getBudgetNumList(int budget) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(ns+"getBudgetNumList",budget);
	}

	public List<RecommendResult> getBudgetAverageRate(int budget_number) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(ns+"getBudgetAverageRate",budget_number);
	}
	
}
