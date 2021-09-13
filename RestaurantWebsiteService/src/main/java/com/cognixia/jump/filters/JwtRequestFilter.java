package com.cognixia.jump.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.cognixia.jump.service.MyUserDetailsService;
import com.cognixia.jump.util.JwtUtil;

// filters in spring, are used to filter through requests or responses and perform some check for security before the 
// request is completed or the response is sent
// this filter will intercept every request coming in and examine the header for tokens

// will label this as a component so spring can recognize it and be able to autowire some of its properties
// and call it to filter requests before sending any kind of response
@Component
public class JwtRequestFilter extends OncePerRequestFilter {
	
	@Autowired
	private MyUserDetailsService userDetailsService;

	@Autowired
	private JwtUtil jwtUtil;
	
	
	// will check header of request to make sure there is a valid jwt to access the API its requesting
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		// this string will contain the jwt sent by the request
		final String authorizationHeader = request.getHeader("Authorization");
		
		String username = null;
		String jwt = null;
		
		// check that we have something in header (so it is not null)
		// then see if there is a jwt that has a token formatted like "Bearer <token>"
		if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			
			// if token is there, grab only the token, don't grab the first part of string that says "Bearer  "
			jwt = authorizationHeader.substring(7);
			
			// then grabbing the user associated with this token
			// if token is not valid, will just return null
			username = jwtUtil.extractUsername(jwt);
		}
		
		// if we found the user and not already in the security context (more info below on this)...
		if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			
			// ...load in their details
			UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
			
			// now check to see that the token is valid based on given user as long as the jwt has not expired
			if( jwtUtil.validateToken(jwt, userDetails) ) {
				
				// all the code below is what is usually done by default by spring security, but since we set up our own
				// token based authentication, need to create an authentication token and placing it in the security context
				
				// security context -> stores info on the currently authenticated user (user who has generated a jwt and is
				// 					   using it to access APIs)
				
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = 
						new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				
				usernamePasswordAuthenticationToken.setDetails(
							new WebAuthenticationDetailsSource().buildDetails(request) );
				
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				
			}
			
		}
		
		// since there may be more filters that will need access to the request, here we finish up what we need to do
		// and pass the request and response down the chain for other filters to access them
		filterChain.doFilter(request, response);
	}

}