package com.cognixia.jump.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.cognixia.jump.model.User;
import com.cognixia.jump.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	UserService service;

	@CrossOrigin(origins= "http://localhost:3000")
	@GetMapping("/user")
	public ResponseEntity<List<User>> getAllUsers() {
		return new ResponseEntity<>(service.findAllUsers(),HttpStatus.OK);
	}
	@CrossOrigin(origins= "http://localhost:3000")
	@GetMapping("/user/{id}")
	public ResponseEntity<User> getUserById(@PathVariable int id) throws ResourceNotFoundException {
		return new ResponseEntity<>(service.findUserById(id),HttpStatus.OK);
	}
	
	@CrossOrigin(origins= "http://localhost:3000")
	@DeleteMapping("/user/{id}")
	public ResponseEntity<User> deleteUserById(@PathVariable int id) throws ResourceNotFoundException {
		return new ResponseEntity<>(service.deleteUserById(id),HttpStatus.OK);
	}
	
	@CrossOrigin(origins= "http://localhost:3000")
	@PutMapping("/user/{id}")
	public ResponseEntity<User> updateUserById(@PathVariable int id, @RequestBody User user) throws ResourceNotFoundException{
		return new ResponseEntity<>(service.updateUser(id, user), HttpStatus.OK);
	}
	
	@CrossOrigin(origins= "http://localhost:3000")
	@PostMapping("/user")
	public ResponseEntity<User> createUser(@RequestBody User user){
		return new ResponseEntity<>(service.createUser(user), HttpStatus.CREATED);

	}
	
	
}


