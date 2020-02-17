package com.ssafy.edu.service;

import java.util.List;

import com.ssafy.edu.model.Budget;
import com.ssafy.edu.model.BudgetInfo;
import com.ssafy.edu.model.BudgetList;
import com.ssafy.edu.model.BudgetListResult;

public interface IBudgetService {
	List<BudgetInfo> getMyBudgetList(String user_email);
	
	BudgetInfo getOneBudgetInfo(int budget_num);

	List<BudgetListResult> getOneBudgetList(int budget_num);

	void deleteBudgetInfo(int budget_num);

	void insertBudgetList(BudgetList budgetlist);

	void insertBudgetInfo(BudgetInfo budgetinfo);

//	void deleteBudgetInfo(String user_email, String budget_title);

//	void deleteBudgetList(String user_email, String budget_title, String pro_id);

//	void updateBudget(BudgetInfo budgetinfo);

	int getLastInsertBudgetNum(String user_email);

	void updateBudget(int budget_num, int suitability);


	
}
