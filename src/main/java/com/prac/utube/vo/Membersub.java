package com.prac.utube.vo;

public class Membersub {
	private String sub_email;
	private String sub_subimg;
	private String sub_subname;
	private String sub_suburl;
	
	public String getSub_email() {
		return sub_email;
	}
	public void setSub_email(String sub_email) {
		this.sub_email = sub_email;
	}
	public String getSub_subimg() {
		return sub_subimg;
	}
	public void setSub_subimg(String sub_subimg) {
		this.sub_subimg = sub_subimg;
	}
	public String getSub_subname() {
		return sub_subname;
	}
	public void setSub_subname(String sub_subname) {
		this.sub_subname = sub_subname;
	}
	public String getsub_suburl() {
		return sub_suburl;
	}
	public void setsub_suburl(String sub_suburl) {
		this.sub_suburl = sub_suburl;
	}
	@Override
	public String toString() {
		return "Membersub [sub_email=" + sub_email + ", sub_subimg=" + sub_subimg + ", sub_subname=" + sub_subname
				+ ", sub_suburl=" + sub_suburl + "]";
	}
	public Membersub(String sub_email, String sub_subimg, String sub_subname, String sub_suburl) {
		super();
		this.sub_email = sub_email;
		this.sub_subimg = sub_subimg;
		this.sub_subname = sub_subname;
		this.sub_suburl = sub_suburl;
	}
	
	public Membersub() {
		
	}
}
