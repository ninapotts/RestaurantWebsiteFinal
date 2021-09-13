package com.cognixia.jump.repository;

import java.util.Optional;

import org.springframework.boot.autoconfigure.domain.EntityScan;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cognixia.jump.model.UserModel;

@Repository
@EntityScan(basePackages = "com.cognixia.jump.model")
public interface UserRepository extends JpaRepository<UserModel, Integer>{
	//User findByUserId(Integer userId);
	
	/// Must be the same exact signature as your models find user name
	Optional<UserModel> findByUserName(String Username);

}


