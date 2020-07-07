package com.ssafy.edu.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class Review implements Serializable{
	private int review_num;
	private int budget_num;
	private String name;
	private String review_content;
	private int like_count;
	private Date review_date;

	public Review() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Review(int review_num, int budget_num, String name, String review_content, int like_count,
			Date review_date) {
		super();
		this.review_num = review_num;
		this.budget_num = budget_num;
		this.name = name;
		this.review_content = review_content;
		this.like_count = like_count;
		this.review_date = review_date;
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

	public String getReview_content() {
		return review_content;
	}

	public void setReview_content(String review_content) {
		this.review_content = review_content;
	}

	public int getLike_count() {
		return like_count;
	}

	public void setLike_count(int like_count) {
		this.like_count = like_count;
	}

	public Date getReview_date() {
		return review_date;
	}

	public void setReview_date(Date review_date) {
		this.review_date = review_date;
	}

	@Override
	public String toString() {
		return "Review [review_num=" + review_num + ", budget_num=" + budget_num + ", name=" + name
				+ ", review_content=" + review_content + ", like_count=" + like_count + ", review_date=" + review_date
				+ "]";
	}

}
