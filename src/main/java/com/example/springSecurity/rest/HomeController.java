package com.example.springSecurity.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HomeController {

	@GetMapping("/")
	public String home() {
		return ("<h1>Welcome Vidhi.</h1>");
	}
	
	@GetMapping("/user")
	public String getUserDetails() {
		return ("<h3>Here are the User details Vidhi.</h3>");
	}
	
	@GetMapping("/admin")
	public String getAdminDetails() {
		return ("<h3>Here are the Admin details Vidhi.</h3>");
	}
	
	@GetMapping("/super-admin")
	public String getSuperAdminDetails() {
		return ("<h3>Here are the Super Admin details Vidhi.</h3>");
	}
}
