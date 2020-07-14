package com.ankit.controller;

// only admin can access this endpoints

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ankit.entity.Employee;
import com.ankit.model.UserEmployee;
import com.ankit.service.AdminService;

@RestController
@RequestMapping("api/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	// method to create new employee of type employee, manager or another admin
	@PostMapping("create")
	public Employee createEmp(@RequestBody Employee employee)  {
		Employee emp = this.adminService.createEmployee(employee);
		return emp;

	}
	
	// get all employee list
	@GetMapping("allemp")
	public List<UserEmployee> getAllEmployees(){
		return adminService.getAllEmployees();
	}
	
	// get all employee having role manager
	@GetMapping("allmanagers")
	public List<UserEmployee> getAllManagers(){
		return adminService.getAllManagers();
	}
	
	// get employee by empid
	@GetMapping("empbyid/{empid}")
	public Employee getEmployeeById(@PathVariable("empid") Integer empid) {
		return adminService.getEmployeeById(empid);
	}

	// get employee by username
	@GetMapping("empbyusername/{username}")
	public Employee getEmployeeByUserName(@PathVariable("username") String userName) {
		return adminService.getEmployeeByUserName(userName);
	}
	
	// delete employee by empid
	@DeleteMapping("deletebyid/{empid}")
	public String deleteEmployeeById(@PathVariable("empid") Integer empid) throws Exception {
		adminService.deleteEmployeeById(empid);
		return "employee with id"+empid+"deleted successfully";
	}
	
	// delete employee by username
	@DeleteMapping("deletename/{username}")
	public String deleteEmployeeByUserName(@PathVariable("username") String username) throws Exception {
		adminService.deleteEmployeeByUserName(username);
		return "employee with username"+username+"deleted successfully";
	}
}
