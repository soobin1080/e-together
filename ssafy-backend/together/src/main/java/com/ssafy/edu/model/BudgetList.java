package com.ssafy.edu.model;

public class BudgetList {
	private int budget_num;
	private Long pro_id;
	private String pro_name;
	private int quantity;
	private int price;

	public BudgetList() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BudgetList(int budget_num, Long pro_id, String pro_name, int quantity, int price) {
		super();
		this.budget_num = budget_num;
		this.pro_id = pro_id;
		this.pro_name = pro_name;
		this.quantity = quantity;
		this.price = price;
	}

	public int getBudget_num() {
		return budget_num;
	}

	public void setBudget_num(int budget_num) {
		this.budget_num = budget_num;
	}

	public Long getPro_id() {
		return pro_id;
	}

	public void setPro_id(Long pro_id) {
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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "BudgetList [budget_num=" + budget_num + ", pro_id=" + pro_id + ", pro_name=" + pro_name + ", quantity="
				+ quantity + ", price=" + price + "]";
	}

}
