package com.ssafy.edu.model;

import java.io.Serializable;

public class Purchase implements Serializable{
	private int pur_id;
	private String pur_title;
	private String email;
	
	public Purchase() {
		super();
	}
	
	public Purchase(int pur_id, String pur_title, String email) {
		super();
		this.pur_id = pur_id;
		this.pur_title = pur_title;
		this.email = email;
	}
	public int getPur_id() {
		return pur_id;
	}
	public void setPur_id(int pur_id) {
		this.pur_id = pur_id;
	}
	public String getPur_title() {
		return pur_title;
	}
	public void setPur_title(String pur_title) {
		this.pur_title = pur_title;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "Purchase [pur_id=" + pur_id + ", pur_title=" + pur_title + ", email=" + email + "]";
	}
	
	
}
