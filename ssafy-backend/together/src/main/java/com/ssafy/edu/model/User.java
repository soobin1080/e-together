package com.ssafy.edu.model;

import java.io.Serializable;

public class User implements Serializable{
	private String email;
	private String pwd;
	private String nickname;
	private int withdraq; //0 이면 회원 // 1이면 탈퇴한 회원
	private int auth ; // 3이면 관리자 // 1이면 사용자
	
	public User() {
		super();
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getWithdraq() {
		return withdraq;
	}
	public void setWithdraq(int withdraq) {
		this.withdraq = withdraq;
	}
	public int getAuth() {
		return auth;
	}
	public void setAuth(int auth) {
		this.auth = auth;
	}
	public User(String email, String pwd, String nickname, int withdraq, int auth) {
		super();
		this.email = email;
		this.pwd = pwd;
		this.nickname = nickname;
		this.withdraq = withdraq;
		this.auth = auth;
	}

	@Override
	public String toString() {
		return "User [email=" + email + ", pwd=" + pwd + ", nickname=" + nickname + ", withdraq=" + withdraq + ", auth="
				+ auth + "]";
	}
	
	
}
