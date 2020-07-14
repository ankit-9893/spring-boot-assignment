package com.ankit.controller;

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
import com.ankit.service.ManagerService;

@RestController
@RequestMapping("api/manager")
public class ManagerController {

	@Autowired
	private ManagerService managerServive;
	
	// create and return new Employee of role employee only
	@PostMapping("create")
	public Employee createEmp(@RequestBody Employee employee ) throws Exception {
		Employee emp = this.managerServive.createEmployee(employee);
		return emp;
	} 
	
	// get details of all employees result depends upon roles
	@GetMapping("allEmp")
	public List<UserEmployee> getAllEmployees(){
		
		 return managerServive.getAllEmployees();
	}
	
	// get employee by username
	@GetMapping("empbyname/{username}")
	public Employee getEmployeeByName(@PathVariable("username") String username) {
		return managerServive.getEmployeeByName(username);
	}
	
	// get employee by empid
	@GetMapping("empbyid/{empid}")
	public Employee getEmployeeByEmpId(@PathVariable("empid") Integer empid) {
		return managerServive.getEmployeeById(empid);
	}
	
	// delete employee by username
	// note manager can delete all employees having role Employee 
	//  Employee having Manager role can be deleted only by admin user  
	@DeleteMapping("deletebyname/{username}")
	public void deleteEmployee(@PathVariable("username") String username) throws Exception {
		 managerServive.deleteEmployee(username);
	}
	
}
