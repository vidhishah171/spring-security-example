package com.example.springSecurity.config;
//package com.example.demo.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//@EnableWebSecurity //tells spring security that this is a web security configuration.
//// Web security is one of the ways in which you can configure security. Others: application/method level security.
//public class SecurityConfig extends WebSecurityConfigurerAdapter{
//
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		// super.configure();
//		auth.inMemoryAuthentication().withUser("vidhi.s").password("vidhi.s").roles("ADMIN").and()
//		.withUser("hina").password("hina").roles("USER").and()
//		.withUser("vidhu").password("vidhu").roles("SUPER");
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
//		//most restrictive : Super-Admin: only certain users can access it. So will put it at the top.
//		http.authorizeRequests()
//		.antMatchers("/super-admin").hasRole("SUPER") //hasAnyRole()
//		.antMatchers("/admin").hasAnyRole("ADMIN","SUPER")
//		.antMatchers("/user").hasAnyRole("USER","ADMIN","SUPER") // because spring security doesn't know the actual privilege of the roles.
//		.antMatchers("/","static/css","static/js").permitAll() //public urls, can't place at top as it'd not work properly and allow every url regardless of role. 
////		Or if used with role, won't allow other roles to access this public urls
//		.and().formLogin();
//		//least restrictive
//	}
//	
//}
