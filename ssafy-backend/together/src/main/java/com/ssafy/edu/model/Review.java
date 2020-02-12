package com.ssafy.edu.model;

public class Review {
	private int review_num;
	private int budget_num;
	private String review_img;
	private String review_content;
	private int like_count;

	public Review() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Review(int review_num, int budget_num, String review_img, String review_content, int like_count) {
		super();
		this.review_num = review_num;
		this.budget_num = budget_num;
		this.review_img = review_img;
		this.review_content = review_content;
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

	public String getReview_img() {
		return review_img;
	}

	public void setReview_img(String review_img) {
		this.review_img = review_img;
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

	@Override
	public String toString() {
		return "Review [review_num=" + review_num + ", budget_num=" + budget_num + ", review_img=" + review_img
				+ ", review_content=" + review_content + ", like_count=" + like_count + "]";
	}

}
