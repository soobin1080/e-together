package com.ssafy.edu.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.edu.dao.BudgetDaoImpl;
import com.ssafy.edu.model.BudgetInfo;
import com.ssafy.edu.model.BudgetList;
import com.ssafy.edu.model.BudgetListResult;

@Service
public class BudgetService implements IBudgetService {

	@Autowired
	private BudgetDaoImpl budgetdao;

	// 내 예산안 보기, 예산안 간략한 형태로 보여주기
	@Override
	public List<BudgetInfo> getMyBudgetList(String user_email) {
		// TODO Auto-generated method stub
		return budgetdao.getMyBudgetList(user_email);
	}

	// 예산안 상세보기
	@Override
	public List<BudgetListResult> getOneBudgetList(int budget_num) {
		// TODO Auto-generated method stub
		return budgetdao.getOneBudgetList(budget_num);
	}

	@Override
	public BudgetInfo getOneBudgetInfo(int budget_num) {
		// TODO Auto-generated method stub
		return budgetdao.getOneBudgetInfo(budget_num);
	}

	// 예산안 저장하기
	@Override
	public void insertBudgetInfo(BudgetInfo budgetinfo) {
		// TODO Auto-generated method stub
		budgetdao.insertBudgetInfo(budgetinfo);
	}

	@Override
	public int getLastInsertBudgetNum(String user_email) {
		// TODO Auto-generated method stub
		return budgetdao.getLastInsertBudgetNum(user_email);
	}

	@Override
	public void insertBudgetList(BudgetList budgetlist) {
		// TODO Auto-generated method stub
		budgetdao.insertBudgetList(budgetlist);
	}

	// 예산안 지우기
	@Override
	public void deleteBudgetInfo(int budget_num) {
		// TODO Auto-generated method stub
		budgetdao.deleteBudgetInfo(budget_num);
	}

	@Override
	public void updateBudget(int budget_num, int suitability) {
		// TODO Auto-generated method stub
		budgetdao.updateBudget(budget_num, suitability);
	}


}
