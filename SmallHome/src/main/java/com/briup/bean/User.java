package com.briup.bean;

public class User {
	private int id;
	private String name;
	private String pwd;
	private String gender;
	private String info;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getCender() {
		return gender;
	}
	public void setCender(String gender) {
		this.gender = gender;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", pwd=" + pwd
				+ ", gender=" + gender + ", info=" + info + "]";
	}
	
}
