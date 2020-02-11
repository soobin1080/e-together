package com.ssafy.edu.model;

public class Reply {

	private String budget_email;
	private String budget_title;
	private String wrtier_email;
	private String content;

	public Reply() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Reply(String budget_email, String budget_title, String wrtier_email, String content) {
		super();
		this.budget_email = budget_email;
		this.budget_title = budget_title;
		this.wrtier_email = wrtier_email;
		this.content = content;
	}

	public String getBudget_email() {
		return budget_email;
	}

	public void setBudget_email(String budget_email) {
		this.budget_email = budget_email;
	}

	public String getBudget_title() {
		return budget_title;
	}

	public void setBudget_title(String budget_title) {
		this.budget_title = budget_title;
	}

	public String getWrtier_email() {
		return wrtier_email;
	}

	public void setWrtier_email(String wrtier_email) {
		this.wrtier_email = wrtier_email;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Reply [budget_email=" + budget_email + ", budget_title=" + budget_title + ", wrtier_email="
				+ wrtier_email + ", content=" + content + "]";
	}

}
