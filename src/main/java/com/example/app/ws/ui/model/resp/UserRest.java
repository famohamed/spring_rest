package com.example.app.ws.ui.model.resp;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserRest {
	
	@NotNull(message = "First Name Null")
	private String firstName;
	@NotNull
	@Size(min = 2, max=16, message = "Password Length violation")
	private String lastName;
	@NotNull
	@Email
	private String email;
	@NotNull
	private String userId;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
	
	
}
