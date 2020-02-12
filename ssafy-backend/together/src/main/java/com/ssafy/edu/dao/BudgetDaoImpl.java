package com.ssafy.edu.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.edu.model.Budget;
import com.ssafy.edu.model.BudgetInfo;
import com.ssafy.edu.model.BudgetList;
import com.ssafy.edu.model.BudgetListResult;

@Repository
public class BudgetDaoImpl {
	String ns = "ssafy.budget.";

	@Autowired
	private SqlSession sqlSession;

	public List<BudgetInfo> getMyBudgetList(String user_email) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(ns + "getBudgetList", user_email);
	}

	public BudgetInfo getOneBudgetInfo(int budget_num) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(ns + "getOneBudgetInfo", budget_num);
	}
	
	public List<BudgetListResult> getOneBudgetList(int budget_num) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(ns + "getOneBudgetList", budget_num);
	}

	public int insertOneBudget(Budget budget) {
		// TODO Auto-generated method stub
		return sqlSession.insert(ns + "insertOneBudget", budget);

	}

	public void insertBudgetInfo(BudgetInfo budgetinfo) {
		// TODO Auto-generated method stub
		sqlSession.insert(ns + "insertBudgetInfo", budgetinfo);
	}

	// 예산안 하나 상세보기
	public void insertBudgetList(BudgetList budgetlist) {
		// TODO Auto-generated method stub
		sqlSession.insert(ns + "insertBudgetList", budgetlist);
	}

	public void deleteBudgetInfo(int budget_num) {
		// TODO Auto-generated method stub
		sqlSession.delete(ns + "deleteBudgetInfo", budget_num);
	}

//	public void deleteBudgetList(String user_email, String budget_title, String pro_id) {
//		// TODO Auto-generated method stub
//		Map<String, Object> paramMap = new HashMap<>();
//		paramMap.put("user_email", user_email);
//		paramMap.put("budget_title", budget_title);
//		paramMap.put("pro_id", pro_id);
//		sqlSession.delete(ns + "deleteBudgetList", paramMap);
//
//	}

	public void updateBudget(int budget_num) {
		// TODO Auto-generated method stub
		sqlSession.update(ns + "updateBudget", budget_num);
	}

	
}
