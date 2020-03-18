package com.assignment.synthesis.config;

import com.assignment.synthesis.constants.Constants;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SpringSecurityConfig  extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.inMemoryAuthentication()
				.withUser(Constants.LIBRARIAN).password(Constants.LIBRARIAN_PASSWORD).roles(Constants.ROLE_LIBRARIAN)
				.and()
				.withUser(Constants.ADMIN).password(Constants.ADMIN_PASSWORD).roles(Constants.ROLE_ADMIN);
		
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
				//HTTP Basic authentication
				.httpBasic()
				.and()
				.authorizeRequests()
				.antMatchers("/h2-console/*").permitAll()
				.antMatchers("/swagger*").permitAll()
				.antMatchers("/issue**").hasRole(Constants.ROLE_LIBRARIAN)
				.antMatchers("/book**").hasRole(Constants.ROLE_LIBRARIAN)
				.antMatchers("/return**").hasRole(Constants.ROLE_LIBRARIAN)
				.antMatchers("/user").hasRole(Constants.ROLE_ADMIN)
				.and()
				.csrf().disable()
				.formLogin().disable();
		http.headers().frameOptions().disable();
		
	}
}
