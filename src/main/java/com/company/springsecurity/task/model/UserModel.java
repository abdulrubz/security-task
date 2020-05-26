package com.company.springsecurity.task.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserModel {
	
	@NotNull(message="cant be empty")
	@Size(min=3, message="must be atleast 3 characters")	
	private String userName;
	
	@NotNull(message="cant be empty")
	@Size(min=6, message="must be atleast 6 characters")
	private String password;
	
	public UserModel() {
		
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [userName=" + userName + ", password=" + password + "]";
	}
	

}
