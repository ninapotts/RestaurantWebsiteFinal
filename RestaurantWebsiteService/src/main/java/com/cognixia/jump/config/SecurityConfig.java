package com.cognixia.jump.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.cognixia.jump.filters.JwtRequestFilter;
import com.cognixia.jump.service.MyUserDetailsService;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	MyUserDetailsService service;
	
	@Autowired
	JwtRequestFilter filter;
	
	// used to check whether the user who is trying to access the API has authority to do so
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(service);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		http.csrf().disable()
//			.authorizeRequests().antMatchers("/authenticate").permitAll()
//			.anyRequest().authenticated();
		
		http.csrf().disable() // disables cross site request forgery (common security attack that executes unwanted actions)
			.authorizeRequests().antMatchers("/authenticate").permitAll() // permit anyone to access "/authenticate" endpoint...
			.anyRequest().authenticated() // ...but all other APIs need a token (needs to be authenticated)
			.and().sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS); // tell spring security not to create sessions b/c not needed 
																 // when we have jwt based security
	
		// makes sure to call filter on request before filter for checking username & password is used
		http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
	}
	
	// just providing a method spring security can use to access the AuthenticationManager needed when authenticating
	// users accessing the APIs (used in autowire within HelloController class)
	@Bean
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
}
