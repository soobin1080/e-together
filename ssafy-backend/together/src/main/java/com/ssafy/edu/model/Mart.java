package com.ssafy.edu.model;

import java.io.Serializable;

public class Mart implements Serializable{
	private int mart_id;
	private String mart_name;
	private String mart_addr;
	private String mart_tel;
	
	public Mart() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Mart(int mart_id, String mart_name, String mart_addr, String mart_tel) {
		super();
		this.mart_id = mart_id;
		this.mart_name = mart_name;
		this.mart_addr = mart_addr;
		this.mart_tel = mart_tel;
	}

	public int getMart_id() {
		return mart_id;
	}

	public void setMart_id(int mart_id) {
		this.mart_id = mart_id;
	}

	public String getMart_name() {
		return mart_name;
	}

	public void setMart_name(String mart_name) {
		this.mart_name = mart_name;
	}

	public String getMart_addr() {
		return mart_addr;
	}

	public void setMart_addr(String mart_addr) {
		this.mart_addr = mart_addr;
	}

	public String getMart_tel() {
		return mart_tel;
	}

	public void setMart_tel(String mart_tel) {
		this.mart_tel = mart_tel;
	}

	@Override
	public String toString() {
		return "Mart [mart_id=" + mart_id + ", mart_name=" + mart_name + ", mart_addr=" + mart_addr + ", mart_tel="
				+ mart_tel + "]";
	}
	
	
}
