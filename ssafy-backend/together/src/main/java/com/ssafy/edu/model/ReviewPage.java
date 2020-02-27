package com.ssafy.edu.model;

import java.util.List;

public class ReviewPage {
	private List<ReviewResult> reviewlist;
	private int currentPage;
	private int startPage;
	private int endPage;
	private int totPage;
	public ReviewPage() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ReviewPage(List<ReviewResult> reviewlist, int currentPage, int startPage, int endPage, int totPage) {
		super();
		this.reviewlist = reviewlist;
		this.currentPage = currentPage;
		this.startPage = startPage;
		this.endPage = endPage;
		this.totPage = totPage;
	}
	public List<ReviewResult> getReviewlist() {
		return reviewlist;
	}
	public void setReviewlist(List<ReviewResult> reviewlist) {
		this.reviewlist = reviewlist;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public int getTotPage() {
		return totPage;
	}
	public void setTotPage(int totPage) {
		this.totPage = totPage;
	}
	@Override
	public String toString() {
		return "ReviewPage [reviewlist=" + reviewlist + ", currentPage=" + currentPage + ", startPage=" + startPage
				+ ", endPage=" + endPage + ", totPage=" + totPage + "]";
	}
	
	
}
