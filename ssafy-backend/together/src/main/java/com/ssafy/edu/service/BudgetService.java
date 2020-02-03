package com.ssafy.edu.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.edu.dao.BudgetDaoImpl;
import com.ssafy.edu.model.Budget;
import com.ssafy.edu.model.BudgetInfo;
import com.ssafy.edu.model.BudgetList;

@Service
public class BudgetService implements IBudgetService {

	@Autowired
	private BudgetDaoImpl budgetdao;

	@Override
	public List<BudgetList> getBudgetList(String user_email) {
		// TODO Auto-generated method stub
		return budgetdao.getBudgetList(user_email);
	}

	@Override
	public Budget getOneBudget(String budget_num, String user_email) {
		// TODO Auto-generated method stub
		return budgetdao.getOneBudget(budget_num, user_email);
	}

//	@Override
//	public int insertOneBudget(Budget budget) {
//		// TODO Auto-generated method stub
//		return budgetdao.insertOneBudget(budget);
//	}

	@Override
	public void insertBudgetList(BudgetList budgetlist) {
		// TODO Auto-generated method stub
		budgetdao.insertBudgetList(budgetlist);
	}

	@Override
	public void insertBudgetInfo(BudgetInfo budgetinfo) {
		// TODO Auto-generated method stub
		budgetdao.insertBudgetInfo(budgetinfo);
	}

}
