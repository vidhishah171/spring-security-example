package com.example.springSecurity.bo;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor // Need it for serialization.
@Getter
@Setter
public class AuthenticationRequest {

	private String username;
	
	private String password;

}
