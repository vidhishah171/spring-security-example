package com.example.springSecurity.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.springSecurity.config.userDetails.service.MyUserDetailsServiceJWT;
import com.example.springSecurity.filters.JwtRequestFilter;

@EnableWebSecurity
public class SecurityConfigJWT extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private MyUserDetailsServiceJWT myUserDetailsService;
	
	@Autowired
	private JwtRequestFilter jwtRequestFilter;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(myUserDetailsService);
	}

	@SuppressWarnings("deprecation")
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

	/**
	 * The csrf.disable configuration option is related to Cross-Site Request Forgery (CSRF) protection in web applications. 
	 * CSRF is a type of attack where an unauthorized user tricks a victim into performing unwanted actions on a website in which the victim is authenticated.
	 * .csrf() indicates csrf protection.
	 * 
	 * CSRF protection is a security mechanism that helps prevent CSRF attacks by adding an anti-CSRF token to requests. 
	 * The .csrf() method configures CSRF protection by enabling the inclusion of this token in HTTP requests and validating it on the server side.
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {

//		to not authenticate when /authenticate method is called. any other request needs to be authenticated. : anyRequest.authenticated
//		to not manage/create sessions. (stateless)
		http.csrf().disable().authorizeRequests().antMatchers("/authenticate").permitAll().anyRequest().authenticated()
		.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//		this filter is called before UsernamePassword filter is called.
		http.addFilterBefore(this.jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
		
//		http.authorizeRequests()
//		.antMatchers("/super-admin").hasRole("SUPER")
//		.antMatchers("/admin").hasAnyRole("ADMIN", "SUPER")
//		.antMatchers("/user").hasAnyRole("USER", "ADMIN", "SUPER")
//		.antMatchers("/", "static/css", "static/js").permitAll()
//		.and().formLogin();
	}
	
	/**
	 * Creating default authentication manager bean.
	 */
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

}
