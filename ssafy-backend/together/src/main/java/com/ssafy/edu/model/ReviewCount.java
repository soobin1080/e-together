package com.ssafy.edu.model;

public class ReviewCount {
	private int review_num;
	private String user_email;

	public ReviewCount() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ReviewCount(int review_num, String user_email) {
		super();
		this.review_num = review_num;
		this.user_email = user_email;
	}

	public int getReview_num() {
		return review_num;
	}

	public void setReview_num(int review_num) {
		this.review_num = review_num;
	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	@Override
	public String toString() {
		return "ReviewCount [review_num=" + review_num + ", user_email=" + user_email + "]";
	}

}
