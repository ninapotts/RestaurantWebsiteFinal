package com.cognixia.jump.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.exception.ResourceNotFoundException;
import com.cognixia.jump.model.AuthenticationRequest;
import com.cognixia.jump.model.AuthenticationResponse;
import com.cognixia.jump.model.UserModel;
import com.cognixia.jump.service.MyUserDetailsService;
import com.cognixia.jump.service.UserService;
import com.cognixia.jump.util.JwtUtil;

@RestController
@RequestMapping("/api")
public class UserController {

// this manager will be what handles the authentication for users
	@Autowired
	private AuthenticationManager manager;
	
	@Autowired
	private MyUserDetailsService detailsService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	
	@Autowired
	UserService service;
	
//	@CrossOrigin(origins= "http://localhost:3000")
//	@GetMapping("/test")
//	public String homey() {
//		return " Welcome adadadad";
//	}

		
	@CrossOrigin(origins= "http://localhost:3000")
	@GetMapping("/")
	public ResponseEntity home() {
		return new ResponseEntity<>(service.findAllUsers(),HttpStatus.OK);
	}

		
	@CrossOrigin(origins= "http://localhost:3000")
	@GetMapping("/user")
	public ResponseEntity<List<UserModel>> getAllUsers() {
		return new ResponseEntity<>(service.findAllUsers(),HttpStatus.OK);
	}
	
	@CrossOrigin(origins= "http://localhost:3000")
	@GetMapping("/user/{id}")
	public ResponseEntity<UserModel> getUserById(@PathVariable int id) throws ResourceNotFoundException {
		return new ResponseEntity<>(service.findUserById(id),HttpStatus.OK);
	}
	
	@CrossOrigin(origins= "http://localhost:3000")
	@DeleteMapping("/user/{id}")
	public ResponseEntity<UserModel> deleteUserById(@PathVariable int id) throws ResourceNotFoundException {
		return new ResponseEntity<>(service.deleteUserById(id),HttpStatus.OK);
	}
	
	@CrossOrigin(origins= "http://localhost:3000")
	@PutMapping("/user/{id}")
	public ResponseEntity<UserModel> updateUserById(@PathVariable int id, @RequestBody UserModel user) throws ResourceNotFoundException{
		return new ResponseEntity<>(service.updateUser(id, user), HttpStatus.OK);
	}
	
	@CrossOrigin(origins= "http://localhost:3000")
	@PostMapping("/user")
	public ResponseEntity<UserModel> createUser(@RequestBody UserModel user)
	{
		return new ResponseEntity<>(service.createUser(user), HttpStatus.CREATED);

	}
	
	
	
	// authentication endpoint -> accepts user id and password and returns a jwt
		//						   -> this jwt will be stored on client  and used as a way to authenticate if a user can access an api
		@CrossOrigin(origins = "http://localhost:3000")
		@PostMapping("/authenticate")
		public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authRequest) throws Exception{
		
			// can set up actual exception handling for spring, but for now this will do so we can get a clear message
			// if we have a bad username or password
			try {
				manager.authenticate(
						new UsernamePasswordAuthenticationToken(
								authRequest.getUsername(), 
								authRequest.getPassword())
						);
			} catch (BadCredentialsException e) {
				throw new Exception("Incorrect Username or Password");
			}
			
			
			// as long as above doesn't throw an exception, can load in this user
			final UserDetails userDetails = detailsService.loadUserByUsername(authRequest.getUsername());
			
			// generate token for this user
			final String jwt = jwtUtil.generateTokens(userDetails);
			
			// now can send a 200 OK response with our jwt
			return ResponseEntity.ok( new AuthenticationResponse(jwt));
		}
	
	
}


