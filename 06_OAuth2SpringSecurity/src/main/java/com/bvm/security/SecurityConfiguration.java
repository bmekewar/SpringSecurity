package com.bvm.security;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@EnableWebSecurity
@EnableOAuth2Sso
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable();
		http
				.authorizeRequests()
				.antMatchers("/home").permitAll()
				//.antMatchers("/**").authenticated()
				//.antMatchers("/user").authenticated().anyRequest().hasAnyRole("ADMIN","USER")
				//.antMatchers("/admin").authenticated().anyRequest().hasRole("ADMIN")
				.antMatchers("/secured").authenticated().anyRequest().permitAll()
				.antMatchers("/**").authenticated().anyRequest().permitAll()
				.anyRequest().authenticated()
				 ;//.and().httpBasic();
	}
	
	
}
