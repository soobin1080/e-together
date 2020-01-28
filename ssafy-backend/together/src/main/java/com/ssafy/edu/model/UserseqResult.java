package com.ssafy.edu.model;

import java.io.Serializable;

public class UserseqResult implements Serializable{

	private static final long serialVersionUID = 1L;

	private int seq;
	private String name;
	private String loginEmail;
	private int count;
	private boolean isLogin ;
	private String state = "succ";
	
	
	public UserseqResult() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserseqResult(int seq, String name, String loginEmail, int count, boolean isLogin, String state) {
		super();
		this.seq = seq;
		this.name = name;
		this.loginEmail = loginEmail;
		this.count = count;
		this.isLogin = isLogin;
		this.state = state;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLoginEmail() {
		return loginEmail;
	}
	public void setLoginEmail(String LoginEmail) {
		this.loginEmail = loginEmail;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	public boolean getIsLogin() {
		return isLogin;
	}
	public void setIsLogin(boolean isLogin) {
		this.isLogin = isLogin;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public String toString() {
		return "UserseqResult [seq=" + seq + ", name=" + name + ", loginEmail=" + loginEmail + ", count=" + count
				+ ", isLogin=" + isLogin + ", state=" + state + "]";
	}
	
	
	
}
