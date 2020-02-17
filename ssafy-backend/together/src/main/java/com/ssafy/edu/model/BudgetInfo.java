package com.ssafy.edu.model;

import java.util.Date;

public class BudgetInfo {
	private int budget_num;
	private String user_email;
	private String budget_title;
	private int personnel;
	private int budget;
	private int suitability;
	private Date budget_date;

	public BudgetInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BudgetInfo(int budget_num, String user_email, String budget_title, int personnel, int budget,
			int suitability, Date budget_date) {
		super();
		this.budget_num = budget_num;
		this.user_email = user_email;
		this.budget_title = budget_title;
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

	public String getBudget_title() {
		return budget_title;
	}

	public void setBudget_title(String budget_title) {
		this.budget_title = budget_title;
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

	public Date getBudget_date() {
		return budget_date;
	}

	public void setBudget_date(Date budget_date) {
		this.budget_date = budget_date;
	}

	@Override
	public String toString() {
		return "BudgetInfo [budget_num=" + budget_num + ", user_email=" + user_email + ", budget_title=" + budget_title
				+ ", personnel=" + personnel + ", budget=" + budget + ", suitability=" + suitability + ", budget_date="
				+ budget_date + "]";
	}

}
