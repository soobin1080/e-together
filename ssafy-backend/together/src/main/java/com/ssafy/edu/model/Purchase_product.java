package com.ssafy.edu.model;

import java.io.Serializable;

public class Purchase_product implements Serializable{
	private int pur_id;
	private int pro_id;
	public Purchase_product() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Purchase_product(int pur_id, int pro_id) {
		super();
		this.pur_id = pur_id;
		this.pro_id = pro_id;
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
	@Override
	public String toString() {
		return "Purchase_product [pur_id=" + pur_id + ", pro_id=" + pro_id + "]";
	}
}
