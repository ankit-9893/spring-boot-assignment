package com.ankit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ankit.entity.Employee;
import com.ankit.model.EmployeePassword;
import com.ankit.service.EmployeeService;

@RestController
@RequestMapping("api/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	// get logged in user details
	@GetMapping("getdetails")
	public Employee getDetails() {
		Employee emp = employeeService.getDetails();
		emp.setPassword(null);
		return emp;
	}

	// to change password of logged in user account
	@PostMapping("changepassword")
	public String changePassword(@RequestBody EmployeePassword password) {
		if (password.getCurrentPassword().contentEquals(password.getUpdatedPassword()))
			return "Cannot update your password, since current password & updated password are same ";
		return employeeService.changePassword(password);
	}

}
