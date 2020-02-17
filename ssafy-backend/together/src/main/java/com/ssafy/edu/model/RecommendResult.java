package com.ssafy.edu.model;

public class RecommendResult {
	private int budget_num;
	private String main_category;
	private int price_sum;

	public RecommendResult() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RecommendResult(int budget_num, String main_category, int price_sum) {
		super();
		this.budget_num = budget_num;
		this.main_category = main_category;
		this.price_sum = price_sum;
	}

	public int getBudget_num() {
		return budget_num;
	}

	public void setBudget_num(int budget_num) {
		this.budget_num = budget_num;
	}

	public String getMain_category() {
		return main_category;
	}

	public void setMain_category(String main_category) {
		this.main_category = main_category;
	}

	public int getPrice_sum() {
		return price_sum;
	}

	public void setPrice_sum(int price_sum) {
		this.price_sum = price_sum;
	}

}
