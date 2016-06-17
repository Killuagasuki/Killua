package com.killua.frame;

public class UserDate {
	String name;
	String password;
	String cpassword;
	String qq;

	public UserDate(String name, String password, String cpassword, String qq) {
		super();
		this.name = name;
		this.password = password;
		this.cpassword = cpassword;
		this.qq = qq;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCpassword() {
		return cpassword;
	}

	public void setCpassword(String cpassword) {
		this.cpassword = cpassword;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

}
