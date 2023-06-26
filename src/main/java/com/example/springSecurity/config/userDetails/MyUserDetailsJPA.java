///**
// * 
// */
//package com.example.springSecurity.config.userDetails;
//
//import java.util.Arrays;
//import java.util.Collection;
//import java.util.List;
//import java.util.stream.Collectors;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
//import com.example.springSecurity.entity.Authority;
//import com.example.springSecurity.entity.User;
//
//import lombok.NoArgsConstructor;
//
///**
// * @author Vidhi_s
// *
// */
//@NoArgsConstructor
//public class MyUserDetailsJPA implements UserDetails {
//
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = 1L;
//	
//	private String username;
//	
//	private String password;
//	
//	private boolean active;
//	
//	private List<GrantedAuthority> authorities;
//
//	public MyUserDetailsJPA(User user, List<Authority> authorities) {
//		this.username = user.getUsername();
//		this.password = user.getPassword();
//		this.active = user.isEnabled();
//		this.authorities = authorities.stream().map(authority -> new SimpleGrantedAuthority(authority.getAuthority()))
//				.collect(Collectors.toList());
//	}
//	
//
//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		return this.authorities;
//	}
//
//	@Override
//	public String getPassword() {
//		return this.password;
//	}
//
//	@Override
//	public String getUsername() {
//		return this.username;
//	}
//
//	@Override
//	public boolean isAccountNonExpired() {
//		return true;
//	}
//
//	@Override
//	public boolean isAccountNonLocked() {
//		return true;
//	}
//
//	@Override
//	public boolean isCredentialsNonExpired() {
//		return true;
//	}
//
//	@Override
//	public boolean isEnabled() {
//		return this.active;
//	}
//
//	
//
//}
