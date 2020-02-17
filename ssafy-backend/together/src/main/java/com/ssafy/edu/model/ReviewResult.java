package com.ssafy.edu.model;

import java.util.Date;
import java.util.List;

public class ReviewResult {
//제목, 작성자,이미지, 예산, 인원, 좋아요수
	private int review_num;
	private int budget_num;
	private String name;
	private String budget_title;
	private int personnel;
	private int budget;
	private int like_count;
	private List<String> like_user;

	public ReviewResult() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ReviewResult(int review_num, int budget_num, String name, String budget_title, int personnel, int budget,
			int like_count, List<String> like_user) {
		super();
		this.review_num = review_num;
		this.budget_num = budget_num;
		this.name = name;
		this.budget_title = budget_title;
		this.personnel = personnel;
		this.budget = budget;
		this.like_count = like_count;
		this.like_user = like_user;
	}

	public int getReview_num() {
		return review_num;
	}

	public void setReview_num(int review_num) {
		this.review_num = review_num;
	}

	public int getBudget_num() {
		return budget_num;
	}

	public void setBudget_num(int budget_num) {
		this.budget_num = budget_num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBudget_title() {
		return budget_title;
	}

	public void setBudget_title(String budget_title) {
		this.budget_title = budget_title;
	}

	public int getPersonnel() {
		return personnel;
	}

	public void setPersonnel(int personnel) {
		this.personnel = personnel;
	}

	public int getBudget() {
		return budget;
	}

	public void setBudget(int budget) {
		this.budget = budget;
	}

	public int getLike_count() {
		return like_count;
	}

	public void setLike_count(int like_count) {
		this.like_count = like_count;
	}

	public List<String> getLike_user() {
		return like_user;
	}

	public void setLike_user(List<String> like_user) {
		this.like_user = like_user;
	}

	@Override
	public String toString() {
		return "ReviewResult [review_num=" + review_num + ", budget_num=" + budget_num + ", name=" + name
				+ ", budget_title=" + budget_title + ", personnel=" + personnel + ", budget=" + budget + ", like_count="
				+ like_count + ", user_email=" + like_user + "]";
	}

}
