package com.ssafy.edu.model;

import java.util.Date;

public class ReplyResult {
	private int reply_num;
	private int review_num;
	private String reply_content;
	private String writer_email;
	private Date reply_date;
	private String name;

	public ReplyResult() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ReplyResult(int reply_num, int review_num, String reply_content, String writer_email, Date reply_date,
			String name) {
		super();
		this.reply_num = reply_num;
		this.review_num = review_num;
		this.reply_content = reply_content;
		this.writer_email = writer_email;
		this.reply_date = reply_date;
		this.name = name;
	}

	public int getReply_num() {
		return reply_num;
	}

	public void setReply_num(int reply_num) {
		this.reply_num = reply_num;
	}

	public int getReview_num() {
		return review_num;
	}

	public void setReview_num(int review_num) {
		this.review_num = review_num;
	}

	public String getReply_content() {
		return reply_content;
	}

	public void setReply_content(String reply_content) {
		this.reply_content = reply_content;
	}

	public String getWriter_email() {
		return writer_email;
	}

	public void setWriter_email(String writer_email) {
		this.writer_email = writer_email;
	}

	public Date getReply_date() {
		return reply_date;
	}

	public void setReply_date(Date reply_date) {
		this.reply_date = reply_date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "ReplyResult [reply_num=" + reply_num + ", review_num=" + review_num + ", reply_content=" + reply_content
				+ ", writer_email=" + writer_email + ", reply_date=" + reply_date + ", name=" + name + "]";
	}

	
}
