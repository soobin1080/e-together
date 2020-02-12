package com.ssafy.edu.model;

public class BudgetInfo {
	private int budget_num;
	private String user_email;
	private int personnel;
	private int budget;
	private int suitability;
	private int budget_date;

	public BudgetInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BudgetInfo(int budget_num, String user_email, int personnel, int budget, int suitability, int budget_date) {
		super();
		this.budget_num = budget_num;
		this.user_email = user_email;
		this.personnel = personnel;
		this.budget = budget;
		this.suitability = suitability;
		this.budget_date = budget_date;
	}

	public int getBudget_num() {
		return budget_num;
	}

	public void setBudget_num(int budget_num) {
		this.budget_num = budget_num;
	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public int getPersonnel() {
		return personnel;
	}

	public void setPersonnel(int personnel) {
		this.personnel = personnel;
	}

	public int getBudget() {
		return budget;
	}

	public void setBudget(int budget) {
		this.budget = budget;
	}

	public int getSuitability() {
		return suitability;
	}

	public void setSuitability(int suitability) {
		this.suitability = suitability;
	}

	public int getBudget_date() {
		return budget_date;
	}

	public void setBudget_date(int budget_date) {
		this.budget_date = budget_date;
	}

	@Override
	public String toString() {
		return "BudgetInfo [budget_num=" + budget_num + ", user_email=" + user_email + ", personnel=" + personnel
				+ ", budget=" + budget + ", suitability=" + suitability + ", budget_date=" + budget_date + "]";
	}

}
