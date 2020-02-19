package com.ssafy.edu.model;

public class Product {
	private String pro_id;
	private String pro_name;
	private int price;
	private String main_category;
	private String weight;
	private String img;

	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Product(String pro_id, String pro_name, int price, String main_category, String weight, String img) {
		super();
		this.pro_id = pro_id;
		this.pro_name = pro_name;
		this.price = price;
		this.main_category = main_category;
		this.weight = weight;
		this.img = img;
	}

	public String getPro_id() {
		return pro_id;
	}

	public void setPro_id(String pro_id) {
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

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
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
				+ main_category + ", weight=" + weight + ", img=" + img + "]";
	}

}
