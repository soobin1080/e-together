package com.ssafy.edu.model;

import java.io.Serializable;

public class Product implements Serializable{
	private int pro_id;
	private String pro_name;
	private int price;
	private String main_category;
	private String sub_category;
	private double gram;
	private double capacity;
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Product(int pro_id, String pro_name, int price, String main_category, String sub_category, double gram,
			double capacity) {
		super();
		this.pro_id = pro_id;
		this.pro_name = pro_name;
		this.price = price;
		this.main_category = main_category;
		this.sub_category = sub_category;
		this.gram = gram;
		this.capacity = capacity;
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
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getMain_category() {
		return main_category;
	}
	public void setMain_category(String main_category) {
		this.main_category = main_category;
	}
	public String getSub_category() {
		return sub_category;
	}
	public void setSub_category(String sub_category) {
		this.sub_category = sub_category;
	}
	public double getGram() {
		return gram;
	}
	public void setGram(double gram) {
		this.gram = gram;
	}
	public double getCapacity() {
		return capacity;
	}
	public void setCapacity(double capacity) {
		this.capacity = capacity;
	}
	@Override
	public String toString() {
		return "Product [pro_id=" + pro_id + ", pro_name=" + pro_name + ", price=" + price + ", main_category="
				+ main_category + ", sub_category=" + sub_category + ", gram=" + gram + ", capacity=" + capacity + "]";
	}

	
}
