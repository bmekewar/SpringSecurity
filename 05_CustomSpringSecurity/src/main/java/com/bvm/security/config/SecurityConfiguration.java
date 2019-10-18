package com.bvm.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.AntPathMatcher;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

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
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/webjars/**","/resources/**");
    }

	/*
	 * @Bean public ClassLoaderTemplateResolver yourTemplateResolver() {
	 * ClassLoaderTemplateResolver configurer = new ClassLoaderTemplateResolver();
	 * configurer.setPrefix("templates/"); configurer.setSuffix(".html");
	 * configurer.setTemplateMode(TemplateMode.HTML);
	 * configurer.setCharacterEncoding("UTF-8"); configurer.setOrder(0); // this is
	 * important. This way spring //boot will listen to both places 0 and 1
	 * configurer.setCacheable(false); configurer.setCheckExistence(true); return
	 * configurer; }
	 */	

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable();
		http
				.authorizeRequests()
				.antMatchers("/login").permitAll()
				.anyRequest().authenticated()
				//.antMatchers("/**").authenticated()
			.and()
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
			.and()
				.formLogin()
				.loginPage("/login").permitAll()
			.and()
				.logout().invalidateHttpSession(true) 
				.clearAuthentication(true)
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/logout-success").permitAll();
	}	
	
	
	@Bean
	public BCryptPasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
