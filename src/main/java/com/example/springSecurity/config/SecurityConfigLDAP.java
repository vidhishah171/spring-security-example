package com.example.springSecurity.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.LdapShaPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfigLDAP extends WebSecurityConfigurerAdapter {
	

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		Authentication
		auth.ldapAuthentication().userDnPatterns("uid={0},ou=people")
		.groupSearchBase("ou=groups")
		.contextSource()
		.url("ldap://localhost:8389/dc=springframework,dc=org")
		.and()
		.passwordCompare()
		.passwordEncoder(new BCryptPasswordEncoder())
		.passwordAttribute("userPassword");
	}

//	@SuppressWarnings("deprecation")
//	@Bean
//	public PasswordEncoder getPasswordEncoder() {
//		return NoOpPasswordEncoder.getInstance();
//	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		Authorization
		http.authorizeRequests().anyRequest().fullyAuthenticated().and().formLogin();
		
//		http.authorizeRequests()
//		.antMatchers("/super-admin").hasRole("SUPER")
//		.antMatchers("/admin").hasAnyRole("ADMIN", "SUPER")
//		.antMatchers("/user").hasAnyRole("USER", "ADMIN", "SUPER")
//		.antMatchers("/", "static/css", "static/js").permitAll()
//		.and().formLogin();
	}

}
