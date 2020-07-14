package com.ankit.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ankit.entity.Employee;

// repository to access data from database

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	
	@Query
	Optional<Employee> findByUserName(String userName);
	
	@Query("select emp from Employee emp where emp.roles=?1")
	List<Employee> findByRoles(String role);
	
	@Query("select emp from Employee emp where emp.userName =?1 and emp.roles=?2")
	Optional<Employee> findEmployee(String userName, String roles);
	
	@Query("select emp from Employee emp where emp.empid =?1 and emp.roles=?2")
	Optional<Employee> findEmployeeById(Integer empid, String roles);
	
}
