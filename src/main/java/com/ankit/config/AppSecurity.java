package com.ankit.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SuppressWarnings("deprecation")
@EnableWebSecurity
public class AppSecurity extends WebSecurityConfigurerAdapter {

	// roles types
	private static final String MANAGER = "MANAGER";
	private static final String EMPLOYEE = "EMPLOYEE";
	private static final String ADMIN = "ADMIN";

	@Autowired
	private UserDetailsService userDetailsService;

	// configuring userdetails service
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}

	// Enable spring http basic security and securing rest point according to the
	// role
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// disabling csrf token
		http.csrf().disable();

		http.authorizeRequests()
			// only admin can access api/admin and other its endpoint
			.antMatchers("/api/admin/**").hasRole(ADMIN)
			// only admin and manager can access api/manager and other its endpoint
			.antMatchers("/api/manager/**").hasAnyRole(MANAGER, ADMIN)
			// anybody can access this endpoints
			.antMatchers("/api/employee/getdetails").hasAnyRole(ADMIN, MANAGER, EMPLOYEE)
			.antMatchers("/all")
			// configuring httpbasic authentication
			.permitAll().and().httpBasic();
	}

	// getting bean of password encoder to encode password
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

}
