package com.ssafy.edu.model;

import java.util.Date;

public class ReviewResult {
//제목, 작성자,이미지, 예산, 인원, 좋아요수
	private int review_num;
	private int budget_num;
	private String name;
	private String budget_title;
	private int personnel;
	private int budget;
	private String budget_img;
	private int like_count;

	public ReviewResult() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ReviewResult(int review_num, int budget_num, String name, String budget_title, int personnel, int budget,
			String budget_img, int like_count) {
		super();
		this.review_num = review_num;
		this.budget_num = budget_num;
		this.name = name;
		this.budget_title = budget_title;
		this.personnel = personnel;
		this.budget = budget;
		this.budget_img = budget_img;
		this.like_count = like_count;
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

	public String getBudget_img() {
		return budget_img;
	}

	public void setBudget_img(String budget_img) {
		this.budget_img = budget_img;
	}

	public int getLike_count() {
		return like_count;
	}

	public void setLike_count(int like_count) {
		this.like_count = like_count;
	}

	@Override
	public String toString() {
		return "ReviewResult [review_num=" + review_num + ", budget_num=" + budget_num + ", name=" + name
				+ ", budget_title=" + budget_title + ", personnel=" + personnel + ", budget=" + budget + ", budget_img="
				+ budget_img + ", like_count=" + like_count + "]";
	}

}
