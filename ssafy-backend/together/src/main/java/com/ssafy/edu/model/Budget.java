package com.ssafy.edu.model;

import java.util.Date;

public class Budget {
	private int budget_num;
	private String user_email;
	private String budget_title;
	private String personnel;
	private String budget;
	private String fitness;
	private Date date;
	private String pro_id;
	private String pro_name;
	private String quantity;
	private String price;
	private int like_count;
	
	public Budget() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Budget(int budget_num, String user_email, String budget_title, String personnel, String budget,
			String fitness, Date date, String pro_id, String pro_name, String quantity, String price, int like_count) {
		super();
		this.budget_num = budget_num;
		this.user_email = user_email;
		this.budget_title = budget_title;
		this.personnel = personnel;
		this.budget = budget;
		this.fitness = fitness;
		this.date = date;
		this.pro_id = pro_id;
		this.pro_name = pro_name;
		this.quantity = quantity;
		this.price = price;
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

	public String getPro_id() {
		return pro_id;
	}

	public void setPro_id(String pro_id) {
		this.pro_id = pro_id;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public int getLike_count() {
		return like_count;
	}

	public void setLike_count(int like_count) {
		this.like_count = like_count;
	}

	public String getPro_name() {
		return pro_name;
	}

	public void setPro_name(String pro_name) {
		this.pro_name = pro_name;
	}

		
}
