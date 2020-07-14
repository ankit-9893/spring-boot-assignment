package com.ankit.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ankit.entity.Employee;
import com.ankit.exception.EmployeeNotFoundException;
import com.ankit.model.UserEmployee;
import com.ankit.repository.EmployeeRepository;

// methods that deals with EmployeeRepository to fetch data from database

@Service
public class AdminService {

	@Autowired
	private EmployeeRepository repository;

	// admin can create manager, employee & another admin as well
	public Employee createEmployee(Employee employee) {
		return repository.save(employee);
	}

	public List<UserEmployee> getAllEmployees() {

		List<Employee> list = new ArrayList<Employee>();

		List<UserEmployee> userEmployee = null;
		
		list = repository.findByRoles("ROLE_EMPLOYEE");
		
		if (list.size() > 0)
			userEmployee = getUserEmployee(list);

		return userEmployee.size() > 0 ? userEmployee : null;
	}

	public List<UserEmployee> getAllManagers() {

		List<Employee> list = new ArrayList<Employee>();

		List<UserEmployee> userEmployee = null;

		list = repository.findByRoles("ROLE_MANAGER");
		if (list.size() > 0)
			userEmployee = getUserEmployee(list);

		return userEmployee.size() > 0 ? userEmployee : null;
	}

	public Employee getEmployeeById(Integer empid) {
		return repository.findById(empid)
				.orElseThrow(() -> new EmployeeNotFoundException("Employee", "employee id", empid));
	}
	
	public Employee getEmployeeByUserName(String userName) {
		return repository.findByUserName(userName)
				.orElseThrow(() -> new EmployeeNotFoundException("Employee", "employee id", userName));
	}
	
	public void deleteEmployeeById(Integer empid) throws Exception {

		Employee employee = repository.findById(empid)
				.orElseThrow(() -> new EmployeeNotFoundException("Employee", "employee username", empid));
		repository.delete(employee);
	}
	
	public void deleteEmployeeByUserName(String username) throws Exception {

		Employee employee = repository.findByUserName(username)
				.orElseThrow(() -> new EmployeeNotFoundException("Employee", "employee username", username));
		repository.delete(employee);
	}

	private List<UserEmployee> getUserEmployee(List<Employee> list) {

		List<UserEmployee> userEmployee = new ArrayList<UserEmployee>();

		for (Employee employee : list) {
			UserEmployee temp = new UserEmployee();
			
			temp.setEmpid(employee.getEmpid());
			temp.setUserName(employee.getUserName());
			temp.setFirstName(employee.getFirstName());
			temp.setLastName(employee.getLastName());
			temp.setDob(employee.getDob());
			temp.setCity(employee.getCity());
			temp.setAddress(employee.getAddress());
			temp.setMobileNo(employee.getMobileNo());
			temp.setRoles(employee.getRoles());

			userEmployee.add(temp);
		}

		return userEmployee;
	}
}
