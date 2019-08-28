package com.ticket.entity;

public class User {

	private int userId;
	private String password;
	private String name;
	private String userName;
	private String email;
	private String role;
	private int departmentId;
	private String homeCompany;
	private String created;
	
	
	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	public int getDepartmentId() {
		return departmentId;
	}


	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}


	public String getHomeCompany() {
		return homeCompany;
	}


	public void setHomeCompany(String homeCompany) {
		this.homeCompany = homeCompany;
	}


	public String getCreated() {
		return created;
	}


	public void setCreated(String created) {
		this.created = created;
	}


	@Override
	public String toString() {
		return "User [userId=" + userId + ", password=" + password + ", name=" + name + ", userName=" + userName
				+ ", email=" + email + ", role=" + role + ", departmentId=" + departmentId + ", homeCompany="
				+ homeCompany + ", created=" + created + "]";
	}
	

}
	
	


