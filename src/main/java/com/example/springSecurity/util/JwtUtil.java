package com.example.springSecurity.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtUtil {

	private String SECRET_KEY = "secret";
	
	public String extractUserName(String token) {
		return this.extractClaim(token, Claims::getSubject);
	}
	
	public Date extractExpiration(String token) {
		return this.extractClaim(token, Claims::getExpiration);
	}
	
	/**
	 * @param <T>
	 * @param token
	 * @param claimsResolver : In order to figure what the claims are as per arg(subject,expiration). Claims: bunch of data that you've passed in. 
	 * @return
	 */
	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = this.extractAllClaims(token);
		return claimsResolver.apply(claims);
	}
	
	private Claims extractAllClaims(String token) {
		return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
	}
	
	private Boolean isTokenExpired(String token) {
		return this.extractExpiration(token).before(new Date());
	}
	
	/**
	 * To generate token using userDetails object : username.
	 * 
	 * @param userDetails
	 * @return
	 */
	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
//		Currently claims are empty but you can pass any specific claims you wanna include in jwt/payload.
		return this.createToken(claims, userDetails.getUsername());
	}

	/**
	 * Just one method we have chosen, can use other ways too.
	 * Expiration Date is current date + 10 hours
	 * @param claims
	 * @param subject : the person who has successfully authenticated
	 * @return
	 */
	private String createToken(Map<String, Object> claims, String subject) {
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
				.signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
	}
	
	public Boolean validateToken(String token, String username, UserDetails userDetails) {
		return (username.equals(userDetails.getUsername()) && !this.isTokenExpired(token));
	}
}
