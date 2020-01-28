package com.ssafy.edu.model;

import java.io.Serializable;

public class Purchase_product implements Serializable{
	private int pur_id;
	private int pro_id;
	private String main_category;
	public Purchase_product() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Purchase_product(int pur_id, int pro_id, String main_category) {
		super();
		this.pur_id = pur_id;
		this.pro_id = pro_id;
		this.main_category = main_category;
	}
	public int getPur_id() {
		return pur_id;
	}
	public void setPur_id(int pur_id) {
		this.pur_id = pur_id;
	}
	public int getPro_id() {
		return pro_id;
	}
	public void setPro_id(int pro_id) {
		this.pro_id = pro_id;
	}
	public String getMain_category() {
		return main_category;
	}
	public void setMain_category(String main_category) {
		this.main_category = main_category;
	}
	@Override
	public String toString() {
		return "Purchase_product [pur_id=" + pur_id + ", pro_id=" + pro_id + ", main_category=" + main_category + "]";
	}
	
}
