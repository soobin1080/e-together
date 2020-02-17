package com.ssafy.edu.model;

public class BudgetListResult {
	private int pro_id;
	private String pro_name;
	private int quantity;
	private String price;

	public BudgetListResult() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BudgetListResult(int pro_id, String pro_name, int quantity, String price) {
		super();
		this.pro_id = pro_id;
		this.pro_name = pro_name;
		this.quantity = quantity;
		this.price = price;
	}

	public int getPro_id() {
		return pro_id;
	}

	public void setPro_id(int pro_id) {
		this.pro_id = pro_id;
	}

	public String getPro_name() {
		return pro_name;
	}

	public void setPro_name(String pro_name) {
		this.pro_name = pro_name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "BudgetListResult [pro_id=" + pro_id + ", pro_name=" + pro_name + ", quantity=" + quantity + ", price="
				+ price + "]";
	}

}
