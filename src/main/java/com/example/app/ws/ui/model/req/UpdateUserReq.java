package com.example.app.ws.ui.model.req;

import javax.validation.constraints.NotNull;

public class UpdateUserReq {

	@NotNull(message = "First Name Null")
	private String firstName;
	
	@NotNull
	private String lastName;

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
}
