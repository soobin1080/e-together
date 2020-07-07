package com.ssafy.edu.model;

import java.io.Serializable;
import java.util.List;

public class ReviewDetailResult implements Serializable {
	private Review review;
	private BudgetInfo budgetinfo;
	private List<BudgetListResult> budgetlist;
	private List<String> like_user;
	private String review_img;

	public ReviewDetailResult() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ReviewDetailResult(Review review, BudgetInfo budgetinfo, List<BudgetListResult> budgetlist,
			List<String> like_user, String review_img) {
		super();
		this.review = review;
		this.budgetinfo = budgetinfo;
		this.budgetlist = budgetlist;
		this.like_user = like_user;
		this.review_img = review_img;
	}

	public Review getReview() {
		return review;
	}

	public void setReview(Review review) {
		this.review = review;
	}

	public BudgetInfo getBudgetinfo() {
		return budgetinfo;
	}

	public void setBudgetinfo(BudgetInfo budgetinfo) {
		this.budgetinfo = budgetinfo;
	}

	public List<BudgetListResult> getBudgetlist() {
		return budgetlist;
	}

	public void setBudgetlist(List<BudgetListResult> budgetlist) {
		this.budgetlist = budgetlist;
	}

	public List<String> getLike_user() {
		return like_user;
	}

	public void setLike_user(List<String> like_user) {
		this.like_user = like_user;
	}

	public String getReview_img() {
		return review_img;
	}

	public void setReview_img(String review_img) {
		this.review_img = review_img;
	}

	@Override
	public String toString() {
		return "ReviewDetailResult [review=" + review + ", budgetinfo=" + budgetinfo + ", budgetlist=" + budgetlist
				+ ", like_user=" + like_user + ", review_img=" + review_img + "]";
	}

}
