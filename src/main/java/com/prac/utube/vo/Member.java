package com.prac.utube.vo;

public class Member {
	private String user_email="";
	private String user_name="";
	private String user_password="";
	private String user_auth="";
	private String user_img="";
	
	
	
	
	public String getUser_img() {
		return user_img;
	}
	public void setUser_img(String user_img) {
		this.user_img = user_img;
	}
	public String getUser_auth() {
		return user_auth;
	}
	public void setUser_auth(String user_auth) {
		this.user_auth = user_auth;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_password() {
		return user_password;
	}
	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}
	
	public Member() {
		
	}
	@Override
	public String toString() {
		return "Member [user_email=" + user_email + ", user_name=" + user_name + ", user_password=" + user_password
				+ ", user_auth=" + user_auth + ", user_img=" + user_img + "]";
	}
	
	
	
}
