package com.example.springSecurity.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springSecurity.bo.AuthenticationRequest;
import com.example.springSecurity.bo.AuthenticationResponse;
import com.example.springSecurity.util.JwtUtil;

@RestController
@RequestMapping("/")
public class AuthenticationController {

	@Autowired
	private JwtUtil jwtTokenUtil;

//	Earlier this used to work in older versions of springboot. Now you need to define this bean.
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailsService userDetailsService;

	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
			throws Exception {

		try {
			this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password.", e);
		}
		final UserDetails userDetails = this.userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		return ResponseEntity.ok(new AuthenticationResponse(this.jwtTokenUtil.generateToken(userDetails)));
	}
}
