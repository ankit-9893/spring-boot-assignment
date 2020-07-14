package com.ankit.model;

import org.springframework.stereotype.Component;

// class to handle password change functionality for the user 

@Component
public class EmployeePassword {

	private String updatedPassword;

	private String currentPassword;

	public String getUpdatedPassword() {
		return updatedPassword;
	}

	public void setUpdatedPassword(String updatedPassword) {
		this.updatedPassword = updatedPassword;
	}

	public String getCurrentPassword() {
		return currentPassword;
	}

	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}
}
