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

	//내 예산안보기
	public List<BudgetInfo> getMyBudgetList(String user_email) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(ns + "getMyBudgetList", user_email);
	}

	//예산안 상세보기
	public BudgetInfo getOneBudgetInfo(int budget_num) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(ns + "getOneBudgetInfo", budget_num);
	}
	
	public List<BudgetListResult> getOneBudgetList(int budget_num) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(ns + "getOneBudgetList", budget_num);
	}

	public void insertBudgetInfo(BudgetInfo budgetinfo) {
		// TODO Auto-generated method stub
		sqlSession.insert(ns + "insertBudgetInfo", budgetinfo);
	}

	public void insertBudgetList(BudgetList budgetlist) {
		// TODO Auto-generated method stub
		sqlSession.insert(ns + "insertBudgetList", budgetlist);
	}

	public void deleteBudgetInfo(int budget_num) {
		// TODO Auto-generated method stub
		sqlSession.delete(ns + "deleteBudgetInfo", budget_num);
	}

//	public void updateBudget(BudgetInfo budgetinfo) {
//		// TODO Auto-generated method stub
//		sqlSession.update(ns + "updateBudget", budgetinfo);
//	}

	public int getLastInsertBudgetNum(String user_email) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(ns+"getLastInsertBudgetNum",user_email);
	}

	public void updateBudget(int budget_num, int suitability) {
		// TODO Auto-generated method stub
		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("budget_num", budget_num);
		paramMap.put("suitability", suitability);
		sqlSession.selectOne(ns+"updateBudget",paramMap);
	}

	public void updateBudgetInfo(BudgetInfo budgetinfo) {
		// TODO Auto-generated method stub
		sqlSession.selectOne(ns+"updateBudgetInfo",budgetinfo);
	}

	
}
