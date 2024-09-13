package com.example.springSecurity.config.userDetails.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.springSecurity.config.userDetails.MyUserDetailsJPA;
import com.example.springSecurity.entity.Authority;
import com.example.springSecurity.repo.AuthorityRepository;
import com.example.springSecurity.repo.UserRepository;

@Service
public class MyUserDetailsServiceJWT implements UserDetailsService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	AuthorityRepository authorityRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

//		Optional<com.example.springSecurity.entity.User> userOpt = this.userRepository.findByUsername(username);
//		userOpt.orElseThrow(() -> new UsernameNotFoundException("Username not found: " + username));
//		List<Authority> authorities = this.authorityRepository.findAllByUsername(username);
//		return new MyUserDetailsJPA(userOpt.get(), authorities);
		return new User("Vidhi","Vidhi", new ArrayList<>());
	}

}