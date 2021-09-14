package com.cognixia.jump.repository;

import java.util.List;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognixia.jump.model.Review;


@Repository
@EntityScan(basePackages = "com.cognixia.jump.model")
public interface ReviewRepository extends JpaRepository<Review, Integer>{
	List<Review> findAllByUserId(Integer user_id);
	List<Review> findAllByRestaurantId(Integer restaurant_id);
}