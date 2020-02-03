package com.ssafy.edu.model;

public class BudgetList {
	private int budget_num;
	private String user_email;
	private String pro_id;
	private String pro_name;
	private String quantity;
	private String price;
	
	public BudgetList() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BudgetList(int budget_num, String user_email, String pro_id, String pro_name,String quantity, String price) {
		super();
		this.budget_num = budget_num;
		this.user_email = user_email;
		this.pro_id = pro_id;
		this.pro_name=pro_name;
		this.quantity = quantity;
		this.price = price;
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
	public String getPro_name() {
		return pro_name;
	}
	public void setPro_name(String pro_name) {
		this.pro_name = pro_name;
	}
	
	
}
