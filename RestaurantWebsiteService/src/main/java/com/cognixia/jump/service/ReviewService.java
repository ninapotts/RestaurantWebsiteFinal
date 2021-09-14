package com.cognixia.jump.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognixia.jump.exception.ResourceNotFoundException;
import com.cognixia.jump.model.Review;
import com.cognixia.jump.repository.ReviewRepository;

@Service
public class ReviewService {
	@Autowired
	ReviewRepository repository;
	
	public List<Review> findAllReviews(){
		return repository.findAll();
	}
	
	public List<Review> findAllByUserId(Integer user_id) {
		
		
		return null;
		
	}
	public List<Review> findAllByRestaurantId(Integer restaurant_id) {
		List<Review> found = repository.findAllByRestaurantId(restaurant_id);
		return found;
		
	}
	
	public Review findReviewById(int id) throws ResourceNotFoundException {
		Optional<Review> found = repository.findById(id);
		
		
		if(found.isEmpty()) {
			throw new ResourceNotFoundException("Review with id " + id + "  not found.");
		}
		
		return found.get();	
	}
	
	public Review deleteReviewById(int id) throws ResourceNotFoundException {
		Review deleted = findReviewById(id);
		repository.deleteById(id);
		return deleted;
	} 
	
	public Review createReview(Review Review) {
		Review.setId(-1);
		Review created = repository.save(Review);
		return created;
	}
	
	public Review updateReview(int id, Review Review) throws ResourceNotFoundException {
		findReviewById(id);
		Review updated = repository.save(Review);
		return updated;
	}
	
	
}