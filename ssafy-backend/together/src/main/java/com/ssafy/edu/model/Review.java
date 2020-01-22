package com.ssafy.edu.model;

import java.io.Serializable;

public class Review implements Serializable{
	private int rev_seq;
	private String rev_nickname;
	private int rev_people;
	private int rev_budget;
	private int rev_proper;
	private int pur_id;
	
	public Review() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Review(int rev_seq, String rev_nickname, int rev_people, int rev_budget, int rev_proper, int pur_id) {
		super();
		this.rev_seq = rev_seq;
		this.rev_nickname = rev_nickname;
		this.rev_people = rev_people;
		this.rev_budget = rev_budget;
		this.rev_proper = rev_proper;
		this.pur_id = pur_id;
	}
	public int getRev_seq() {
		return rev_seq;
	}
	public void setRev_seq(int rev_seq) {
		this.rev_seq = rev_seq;
	}
	public String getRev_nickname() {
		return rev_nickname;
	}
	public void setRev_nickname(String rev_nickname) {
		this.rev_nickname = rev_nickname;
	}
	public int getRev_people() {
		return rev_people;
	}
	public void setRev_people(int rev_people) {
		this.rev_people = rev_people;
	}
	public int getRev_budget() {
		return rev_budget;
	}
	public void setRev_budget(int rev_budget) {
		this.rev_budget = rev_budget;
	}
	public int getRev_proper() {
		return rev_proper;
	}
	public void setRev_proper(int rev_proper) {
		this.rev_proper = rev_proper;
	}
	public int getPur_id() {
		return pur_id;
	}
	public void setPur_id(int pur_id) {
		this.pur_id = pur_id;
	}
	@Override
	public String toString() {
		return "Review [rev_seq=" + rev_seq + ", rev_nickname=" + rev_nickname + ", rev_people=" + rev_people
				+ ", rev_budget=" + rev_budget + ", rev_proper=" + rev_proper + ", pur_id=" + pur_id + "]";
	}
	
	
}
