//package com.example.springSecurity.config.userDetails;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import com.example.springSecurity.entity.Authority;
//import com.example.springSecurity.entity.User;
//import com.example.springSecurity.repo.AuthorityRepository;
//import com.example.springSecurity.repo.UserRepository;
//
//@Service
//public class MyUserDetailsService implements UserDetailsService {
//
//	@Autowired
//	UserRepository userRepository;
//
//	@Autowired
//	AuthorityRepository authorityRepository;
//
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//		Optional<User> userOpt = this.userRepository.findByUsername(username);
//		userOpt.orElseThrow(() -> new UsernameNotFoundException("Username not found: " + username));
//		List<Authority> authorities = this.authorityRepository.findAllByUsername(username);
//		return new MyUserDetailsJPA(userOpt.get(), authorities);
//	}
//
//}