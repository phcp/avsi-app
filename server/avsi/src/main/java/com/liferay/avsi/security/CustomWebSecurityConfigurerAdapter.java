package com.liferay.avsi.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.Component;

/**
 * @author Leonardo Barros
 */
@Component
@EnableWebSecurity
public class CustomWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

	@Autowired
	private AuthenticationProvider authProvider;

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.csrf().disable().authorizeRequests()
			.antMatchers(HttpMethod.POST,"/users").permitAll()
			.antMatchers(HttpMethod.POST,"/products").hasAuthority("producer")
			.antMatchers(HttpMethod.POST,"/orders").hasAuthority("client")
			.antMatchers("/products/*").hasAuthority("manager")
			.antMatchers("/orders/*").hasAuthority("manager")
			.antMatchers("/*").hasAuthority("admin")
			.anyRequest().authenticated()
			.and().httpBasic();
	}

	@Autowired
	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) {
		authenticationManagerBuilder.authenticationProvider(authProvider);
	}
}
