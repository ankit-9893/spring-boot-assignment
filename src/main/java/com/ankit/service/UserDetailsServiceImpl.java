package com.ankit.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ankit.entity.Employee;
import com.ankit.repository.EmployeeRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private EmployeeRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

		Optional<Employee> user = userRepository.findByUserName(userName);

		user.orElseThrow(() -> new UsernameNotFoundException(userName + " not found."));
		
//		Employee user1 = user.get();
//		System.out.println("inside service");
//		System.out.println(user1.getUserName());
//		System.out.println(user1.getPassword());
//		System.out.println(user1.getRoles());
//		System.out.println(user1.getEmpid());

		return user.map(UserDetailsImpl::new).get();
	}

}
