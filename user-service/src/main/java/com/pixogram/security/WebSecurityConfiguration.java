package com.pixogram.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.pixogram.service.UserService;

@Configuration
@Order(1)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Value("${security.login.page}")
	private String securityLoginPage;
	
	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private UserService userService;
	
	@Bean
	public UserDetailsService userDetailsService() {
	    return super.userDetailsService();
	}
	
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
	
		http
		.authorizeRequests()
		.antMatchers("/login").permitAll()
		.antMatchers("/oauth/authorize")
		.authenticated()
		.and()
		.formLogin()
		.loginPage(securityLoginPage)
		.permitAll()
		.and().requestMatchers()
        	.antMatchers("/login","/oauth/authorize");	
			

	}
	
	
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		userService.createDefaultUser();
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
	    web
	        .ignoring()
	        .antMatchers("/user/check/**").antMatchers("/user/register/**");
	}
	

	
	
	
}