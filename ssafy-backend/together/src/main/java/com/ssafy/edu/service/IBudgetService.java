package com.ssafy.edu.service;

import java.util.List;

import com.ssafy.edu.model.Budget;
import com.ssafy.edu.model.BudgetInfo;
import com.ssafy.edu.model.BudgetList;

public interface IBudgetService {
	List<BudgetList> getBudgetList(String user_email);
	
	Budget getOneBudget(String budget_num,String user_email);
	
//	int insertOneBudget(Budget budget);

	void insertBudgetList(BudgetList budgetlist);

	void insertBudgetInfo(BudgetInfo budgetinfo);
}
