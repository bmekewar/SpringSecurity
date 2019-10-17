package com.bvm.security;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.LdapShaPasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
		.ldapAuthentication()
			.userDnPatterns("uid={0},ou=people")
			.groupSearchBase("ou=groups")
			.contextSource()
				.url("ldap://localhost:8389/dc=springframework,dc=org")
				.and()
			.passwordCompare()
				.passwordEncoder(new LdapShaPasswordEncoder())
				.passwordAttribute("userPassword");	
	}

	/*
	 * @Bean public DefaultSpringSecurityContextSource contextSource() {
	 * 
	 * return new
	 * DefaultSpringSecurityContextSource(Arrays.asList("ldap://localhost:8389"),
	 * "dc=springframework,dc=org"); }
	 */	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
			.anyRequest()
			.fullyAuthenticated()
			.and()
			.formLogin();
//			.failureUrl("/login?error").permitAll()
//			.and()
//	        .logout()
//	        .deleteCookies("remove")
//	        .invalidateHttpSession(true)
//	        .logoutUrl("/logout")
//	        .logoutSuccessUrl("/login?logout")
//	        .and()
		    // Cross-site request forgery is turned off for RESTful API calls with the assumption that
		    // authentication will be sufficient protection
//		    .csrf().ignoringAntMatchers("/api/**", "/space/{\\d+}/**", "/admin/**");	
	}
}
