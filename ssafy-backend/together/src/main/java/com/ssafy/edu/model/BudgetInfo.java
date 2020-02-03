package com.ssafy.edu.model;

import java.util.Date;

public class BudgetInfo {
	private int budget_num;
	private String user_email;
	private String budget_title;
	private String personnel;
	private String budget;
	private String fitness;
	private Date date;
	private int like_count;
	
	public BudgetInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BudgetInfo(int budget_num, String user_email, String budget_title, String personnel, String budget,
			String fitness, Date date, int like_count) {
		super();
		this.budget_num = budget_num;
		this.user_email = user_email;
		this.budget_title = budget_title;
		this.personnel = personnel;
		this.budget = budget;
		this.fitness = fitness;
		this.date = date;
		this.like_count = like_count;
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

	public String getPersonnel() {
		return personnel;
	}

	public void setPersonnel(String personnel) {
		this.personnel = personnel;
	}

	public String getBudget() {
		return budget;
	}

	public void setBudget(String budget) {
		this.budget = budget;
	}

	public String getFitness() {
		return fitness;
	}

	public void setFitness(String fitness) {
		this.fitness = fitness;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getLike_count() {
		return like_count;
	}

	public void setLike_count(int like_count) {
		this.like_count = like_count;
	}

}
