package com.ssafy.edu.springjwt.payload.request;

import java.util.Set;

import javax.validation.constraints.*;
 
public class SignupRequest {

    private String name;
 
    private String email;
    
    private String pwd;
    
    private int count;
    private String temp_pwd;
    private int withdraw; //0 이면 회원 // 1이면 탈퇴한 회원
    private int auth ; // 3이면 관리자 // 1이면 사용자
    private String phone;
    
    
    
	public SignupRequest(String name, String email, String pwd, int count, String temp_pwd, int withdraw, int auth,
			String phone) {
		super();
		this.name = name;
		this.email = email;
		this.pwd = pwd;
		this.count = count;
		this.temp_pwd = temp_pwd;
		this.withdraw = withdraw;
		this.auth = auth;
		this.phone = phone;
	}
	public SignupRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getTemp_pwd() {
		return temp_pwd;
	}
	public void setTemp_pwd(String temp_pwd) {
		this.temp_pwd = temp_pwd;
	}
	public int getWithdraw() {
		return withdraw;
	}
	public void setWithdraw(int withdraw) {
		this.withdraw = withdraw;
	}
	public int getAuth() {
		return auth;
	}
	public void setAuth(int auth) {
		this.auth = auth;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
    
    
}