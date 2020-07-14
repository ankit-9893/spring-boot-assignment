package com.ankit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.ankit.entity.Employee;
import com.ankit.exception.EmployeeNotFoundException;
import com.ankit.model.EmployeePassword;
import com.ankit.repository.EmployeeRepository;

// This class provides the functionality to the logged in user

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository repository;

	public Employee getDetails() {

		// to get current logged in user-name
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		String username;

		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
			System.out.println("userdetails :- username => " + username);
		} else {
			username = principal.toString();
		}

		Employee employee = repository.findByUserName(username)
				.orElseThrow(() -> new EmployeeNotFoundException("Employee", "employee name", username));

		return employee;
	}

	public String changePassword(EmployeePassword employeePassword) {

		Employee employee = getDetails();
		String password = employee.getPassword();
		System.out.println("emp pawsd" + password);
		if (employeePassword.getCurrentPassword().equals(password)) {
			password = employeePassword.getUpdatedPassword();
			employee.setPassword(password);
			repository.save(employee);
			return "Password changed successfully";
		}

		return "Password cannot be changed";

	}

}
