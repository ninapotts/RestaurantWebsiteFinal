package com.cognixia.jump.service;

import java.util.ArrayList;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cognixia.jump.model.UserModel;
import com.cognixia.jump.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService{
//
	@Autowired
	UserRepository userRepo;
	
	
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//	
//		return new User("foo", "foo", new ArrayList<>());
//	}
	
	// method will be called by Spring to load a user when you need to authenticate/authorize api requests
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		
//		//Removing the hardcoded user
//		if (username.equals("user1")) {
//			return new User("user1", "{noop}123", new ArrayList<>());
//		}
//		
//		
//		System.out.println("Username Not Found");
//		throw new UsernameNotFoundException(username);
//		
		
		// Checking the database for a matching username, and returning a 
		// user data object
		Optional<UserModel> found = userRepo.findByUserName(username);
		
		if (found.isEmpty()) {
			//We can implement exception handling to a complete app, 
			//but for testing a thrown exception is sufficient
			throw new UsernameNotFoundException(username);
		}
		// Prefixing a retrieved password with {noop} to avoid checking for an excoding
		return new User(found.get().getUserName(),"{noop}" + found.get().getPassword(), new ArrayList<>());
		
		
	}

}
