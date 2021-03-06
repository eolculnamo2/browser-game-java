package com.game.configs;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	//add reference to data source in AppConfig
	@Autowired
	private DataSource securityDataSource;
	@Override
	//Right click > Source > Override/Implement Methods to find this
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//jdbc authentication
		auth.jdbcAuthentication().dataSource(securityDataSource);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//login
		http.authorizeRequests()
			.anyRequest().authenticated()
			.and().formLogin()
			.loginPage("/login")
			.loginProcessingUrl("/login-user")
			.permitAll()
			.and()
			.logout().permitAll()
			.and()
	        .csrf().disable();
	}
	


}
