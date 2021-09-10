package com.cognixia.jump.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognixia.jump.exception.ResourceNotFoundException;
import com.cognixia.jump.model.User;
import com.cognixia.jump.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository repository;
	
	public List<User> findAllUsers(){
		return repository.findAll();
	}
	
	public User findUserById(int id) throws ResourceNotFoundException {
		Optional<User> found = repository.findById(id);
		
		if(found.isEmpty()) {
			throw new ResourceNotFoundException("User with id " + id + "  not found.");
		}
		
		return found.get();	
	}
	
	public User deleteUserById(int id) throws ResourceNotFoundException {
		User deleted = findUserById(id);
		repository.deleteById(id);
		return deleted;
	}
	
	public User createUser(User user) {
		user.setUserId(-1);
		User created = repository.save(user);
		return created;
	}
	
	public User updateUser(int id, User user) throws ResourceNotFoundException {
		findUserById(id);
		User updated = repository.save(user);
		return updated;
	}
}
