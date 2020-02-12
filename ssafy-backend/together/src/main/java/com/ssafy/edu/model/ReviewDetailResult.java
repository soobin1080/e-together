package com.ssafy.edu.model;

import java.util.List;

public class ReviewDetailResult {
	private Review review;
	private BudgetInfo budgetinfo;
	private List<BudgetListResult> budgetlist;

	public ReviewDetailResult() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ReviewDetailResult(Review review, BudgetInfo budgetinfo, List<BudgetListResult> budgetlist) {
		super();
		this.review = review;
		this.budgetinfo = budgetinfo;
		this.budgetlist = budgetlist;
	}

	public Review getReview() {
		return review;
	}

	public void setReview(Review review) {
		this.review = review;
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
		return "ReviewDetailResult [review=" + review + ", budgetinfo=" + budgetinfo + ", budgetlist=" + budgetlist
				+ "]";
	}

}
