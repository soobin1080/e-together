package com.ssafy.edu.model;

import java.util.Date;
import java.util.List;

public class Budget {
	private BudgetInfo budgetinfo;
	private List<BudgetListResult> budgetlist;

	public Budget() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Budget(BudgetInfo budgetinfo, List<BudgetListResult> budgetlist) {
		super();
		this.budgetinfo = budgetinfo;
		this.budgetlist = budgetlist;
	}

	public BudgetInfo getBudgetinfo() {
		return budgetinfo;
	}

	public void setBudgetinfo(BudgetInfo budgetinfo) {
		this.budgetinfo = budgetinfo;
	}

	public List<BudgetListResult> getBudgetlist() {
		return budgetlist;
	}

	public void setBudgetlist(List<BudgetListResult> budgetlist) {
		this.budgetlist = budgetlist;
	}

	@Override
	public String toString() {
		return "Budget [budgetinfo=" + budgetinfo + ", budgetlist=" + budgetlist + "]";
	}

}
