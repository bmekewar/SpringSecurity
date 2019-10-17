package com.bvm.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.bvm.security.service.CustomUserDetailsService;

@EnableWebSecurity
@EnableJpaRepositories(basePackages = "com.bvm.security.repository")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomUserDetailsService userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(this.getPasswordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable();
		http
				.authorizeRequests()
				.antMatchers("/user").authenticated().anyRequest().hasAnyRole("ADMIN","USER")
			.and()
				.authorizeRequests()
				.antMatchers("/admin").authenticated().anyRequest().hasRole("ADMIN")
			.and()	
				.authorizeRequests()
				.antMatchers("/secured").authenticated().anyRequest().permitAll()
			.and()
				.authorizeRequests()
				.antMatchers("/**").authenticated().anyRequest().permitAll()
			.and().formLogin().permitAll()
			.and()
				.formLogin().failureUrl("/login?error").permitAll()
			.and()
				.logout().permitAll();
		
	}	
	
	
	@Bean
	public BCryptPasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
