package com.ankit.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ankit.entity.Employee;
import com.ankit.exception.EmployeeNotFoundException;
import com.ankit.model.UserEmployee;
import com.ankit.repository.EmployeeRepository;

// this class provides services to the manager controller

@Service
public class ManagerService {

	@Autowired
	private EmployeeRepository repository;

	@Autowired
	EmployeeService employeeService;

	public Employee createEmployee(Employee employee) throws Exception {
		String role = employee.getRoles();
		if (role.equals("ROLE_EMPLOYEE"))
			return repository.save(employee);
		throw new Exception("Manager cannot create employee of reole " + role);
	}

	// service to get details of all employees of role employee
	public List<UserEmployee> getAllEmployees() {

		List<Employee> list = new ArrayList<Employee>();

		List<UserEmployee> userEmployee = null;

		// manager can see only general employees
		list = repository.findByRoles("ROLE_EMPLOYEE");
		if (list.size() > 0)
			userEmployee = getUserEmployee(list);

		return userEmployee.size() > 0 ? userEmployee : null;
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

	public Employee getEmployeeByName(String username) {
		return repository.findEmployee(username, "ROLE_EMPLOYEE")
				.orElseThrow(() -> new EmployeeNotFoundException("Employee", "employee username", username));
	}

	public void deleteEmployee(String username) throws Exception {
		Employee employee = getEmployeeByName(username);
		String role = employee.getRoles();
		if (role == "MANAGER")
			throw new Exception("Only Admin can delete user having role manager");
		repository.delete(employee);
	}

	public Employee getEmployeeById(Integer empid) {
		return repository.findEmployeeById(empid, "ROLE_EMPLOYEE")
				.orElseThrow(() -> new EmployeeNotFoundException("Employee", "employee id", empid));
	}
}
