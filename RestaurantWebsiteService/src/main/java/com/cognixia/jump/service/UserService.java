package com.cognixia.jump.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognixia.jump.exception.ResourceNotFoundException;
import com.cognixia.jump.model.UserModel;
import com.cognixia.jump.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository repository;
	
	public List<UserModel> findAllUsers(){
		return repository.findAll();
	}
	
	public UserModel findUserById(int id) throws ResourceNotFoundException {
		Optional<UserModel> found = repository.findById(id);
		
		if(found.isEmpty()) {
			throw new ResourceNotFoundException("User with id " + id + "  not found.");
		}
		
		return found.get();	
	}
	
	public UserModel deleteUserById(int id) throws ResourceNotFoundException {
		UserModel deleted = findUserById(id);
		repository.deleteById(id);
		return deleted;
	}
	
	public UserModel createUser(UserModel user) {
		user.setId(-1);
		UserModel created = repository.save(user);
		return created;
	}
	
	public UserModel updateUser(int id, UserModel user) throws ResourceNotFoundException {
		findUserById(id);
		UserModel updated = repository.save(user);
		return updated;
	}
}
