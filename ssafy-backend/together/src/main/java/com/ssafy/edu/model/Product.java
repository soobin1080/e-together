package com.ssafy.edu.model;

import java.io.Serializable;

public class Product implements Serializable{
	private int pro_id;
	private String pro_name;
	private int price;
	private double gram;
	private int mart_id;
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Product(int pro_id, String pro_name, int price, double gram, int mart_id) {
		super();
		this.pro_id = pro_id;
		this.pro_name = pro_name;
		this.price = price;
		this.gram = gram;
		this.mart_id = mart_id;
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
	public double getGram() {
		return gram;
	}
	public void setGram(double gram) {
		this.gram = gram;
	}
	public int getMart_id() {
		return mart_id;
	}
	public void setMart_id(int mart_id) {
		this.mart_id = mart_id;
	}
	@Override
	public String toString() {
		return "Product [pro_id=" + pro_id + ", pro_name=" + pro_name + ", price=" + price + ", gram=" + gram
				+ ", mart_id=" + mart_id + "]";
	}
	
	
}
