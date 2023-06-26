package com.example.springSecurity.config;
//package com.example.demo.config;
//
//import javax.sql.DataSource;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//@EnableWebSecurity
//public class SecurityConfigInMemory extends WebSecurityConfigurerAdapter {
//
//  /** you need to configure the datasource somewhere else and pass it to jdbc authentication.
//	* But in this case we have added an embedded database as h2.
//	* when you add an embedded database in springboot app, it automatically create that datasource by itself. */
//	@Autowired
//	DataSource dataSource;
//
//	/*
//	 * @Override protected void configure(AuthenticationManagerBuilder auth) throws
//	 * Exception {
//	 * auth.jdbcAuthentication().dataSource(dataSource).withDefaultSchema().withUser
//	 * (User.withUsername("user").password("user").roles("USER"))
//	 * .withUser(User.withUsername("admin").password("admin").roles("ADMIN"))
//	 * .withUser(User.withUsername("super-admin").password("super-admin").roles(
//	 * "SUPER")); }
//	 */
//
//	/**
//	 * When not using a default schema and using customized one.
//	 */
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.jdbcAuthentication().dataSource(dataSource)
//				.usersByUsernameQuery("select username,password,enabled from my_users where username = ?")
//				.authoritiesByUsernameQuery("select username,authority from my_authorities where username = ?");
//	}
//
//	@SuppressWarnings("deprecation")
//	@Bean
//	public PasswordEncoder getPasswordEncoder() {
//		return NoOpPasswordEncoder.getInstance();
//	}
//
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//
////	    most restrictive : Super-admin: only certain users can access it. So will put it at the top.
//		http.authorizeRequests().antMatchers("/super-admin").hasRole("SUPER").antMatchers("/admin")
//				.hasAnyRole("ADMIN", "SUPER").antMatchers("/user").hasAnyRole("USER", "ADMIN", "SUPER")
////      because spring security doesn't know the actual privilege of the roles.
//				.antMatchers("/", "static/css", "static/js").permitAll()
////	    public urls, can't place at top as it'd not work properly and allow every url regardless of role.
////		Or if used with role, won't allow other roles to access this public urls.
//				.and().formLogin();
////		least restrictive
//	}
//
//}
