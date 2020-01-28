package com.ssafy.edu.model;

import java.io.Serializable;

public class Product implements Serializable{
	private int pro_id;
	private String pro_name;
	private int price;
	private String main_category;
	private double gram;
	private String img;
	
	public Product() {
		super();
	}
	
	public Product(int pro_id, String pro_name, int price, String main_category, double gram, String img) {
		super();
		this.pro_id = pro_id;
		this.pro_name = pro_name;
		this.price = price;
		this.main_category = main_category;
		this.gram = gram;
		this.img = img;
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

	public double getGram() {
		return gram;
	}

	public void setGram(double gram) {
		this.gram = gram;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	@Override
	public String toString() {
		return "Product [pro_id=" + pro_id + ", pro_name=" + pro_name + ", price=" + price + ", main_category="
				+ main_category + ", gram=" + gram + ", img=" + img + "]";
	}

	
	
	
	
}
