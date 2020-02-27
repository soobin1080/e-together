package com.ssafy.edu.model;

import java.util.List;

public class ProductPage {
	private List<Product> productlist;
	private int currentPage;
	private int startPage;
	private int endPage;
	private int totPage;

	public ProductPage() {
	}

	public ProductPage(List<Product> productlist, int currentPage, int startPage, int endPage, int totPage) {
		super();
		this.productlist = productlist;
		this.currentPage = currentPage;
		this.startPage = startPage;
		this.endPage = endPage;
		this.totPage = totPage;
	}

	public List<Product> getProductlist() {
		return productlist;
	}

	public void setProductlist(List<Product> productlist) {
		this.productlist = productlist;
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
		return "ProductPage [productlist=" + productlist + ", currentPage=" + currentPage + ", startPage=" + startPage
				+ ", endPage=" + endPage + ", totPage=" + totPage + "]";
	}
}
