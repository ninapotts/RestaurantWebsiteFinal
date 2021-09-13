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
import com.cognixia.jump.model.Review;
import com.cognixia.jump.service.ReviewService;

@RestController
@RequestMapping("/api")
public class ReviewController {

	@Autowired
	ReviewService service;

	@CrossOrigin(origins= "http://localhost:8080")
	@GetMapping("/review")
	public ResponseEntity<List<Review>> getAllReviews() {
		return new ResponseEntity<>(service.findAllReviews(),HttpStatus.OK);
	}
	
	@GetMapping("/review/{id}")
	public ResponseEntity<Review> getReviewById(@PathVariable int id) throws ResourceNotFoundException {
		return new ResponseEntity<>(service.findReviewById(id),HttpStatus.OK);
	}
	
	@DeleteMapping("/review/{id}")
	public ResponseEntity<Review> deleteReviewById(@PathVariable int id) throws ResourceNotFoundException {
		return new ResponseEntity<>(service.deleteReviewById(id),HttpStatus.OK);
	}
	
	@PutMapping("/review/{id}")
	public ResponseEntity<Review> updateReviewById(@PathVariable int id, @RequestBody Review review) throws ResourceNotFoundException{
		return new ResponseEntity<>(service.updateReview(id, review), HttpStatus.OK);
	}
	
	@CrossOrigin(origins= "http://localhost:3000")
	@PostMapping("/review")
	public ResponseEntity<Review> createReview(@RequestBody Review review){
		return new ResponseEntity<>(service.createReview(review), HttpStatus.CREATED);

	}
	
	
}


