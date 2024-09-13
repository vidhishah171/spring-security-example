package com.example.springSecurity.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.springSecurity.config.userDetails.service.MyUserDetailsServiceJWT;
import com.example.springSecurity.util.JwtUtil;

/**
 * @author Vidhi_s
 * 
 *         OncePerRequest filter intercepts every request only once. Runs once
 *         per request.
 *
 */
@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	private static final String AUTHORIZATION = "Authorization";

	private static final String BEARER = "Bearer ";

	@Autowired
	private MyUserDetailsServiceJWT userDetailsService;

	@Autowired
	private JwtUtil jwtUtil;

	/**
	 * @param filterChain : Option of passing on to the next filter in filterChain, or ending the request right there.
	 * Examines incoming request from jwt header, it's going to look at the right header and see if that jwt is valid.
	 * If it finds a valid jwt, it's gonna get the userdetails from user details service and save it in the security context.
	 * Needs userdetails: to find user, if it finds jwt in the header, needs jwtUtil: to verify the jwt. and also pullup the username from it.
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		final String authorizationToken = request.getHeader(AUTHORIZATION);
		String username = null;
		String jwt = null;
		if(authorizationToken!=null && authorizationToken.startsWith(BEARER)) {
			jwt = authorizationToken.substring(7);
			try {
			username = this.jwtUtil.extractUserName(jwt);
			} catch(Exception e) {
			}
		}
		/** Setting .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) will lead to Spring Security
		 *  using a NullSecurityContextRepository, instead of the default HttpSessionSecurityContextRepository.
		 *  It is a simple implementation, in that it will simply not save anything to the HTTP Session and, for every request, 
		 *  create a completely new and empty SecurityContext, hence with no stored authentication etc.
		 */
//		Only if user context does not already have an authenticated user.
		if(SecurityContextHolder.getContext().getAuthentication() == null) {
		if(username!=null) {

			UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
			if(this.jwtUtil.validateToken(jwt, username, userDetails)) {
				/**
				 * UsernamePasswordAuthenticationToken is the default token spring security uses
				 * for managing authentication in the context of username and password. 
				 * Here we're gonna create a new username and password authentication token and set
				 * the details which is in request.
				 */
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = 
						new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
			else {
				System.out.println("Token is not validated.");
			}
		} else {
			System.out.println("Username null.");
		
		}
		} else {
			System.out.println("In the else part where username = " + username);
		}
		filterChain.doFilter(request, response);
	}

}
