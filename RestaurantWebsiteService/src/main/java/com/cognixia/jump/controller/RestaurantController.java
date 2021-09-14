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
import com.cognixia.jump.model.Restaurant;
import com.cognixia.jump.service.RestaurantService;

@RestController
@RequestMapping("/api")
public class RestaurantController {

	@Autowired
	RestaurantService service;

	@CrossOrigin(origins= "http://localhost:3000")
	@GetMapping("/restaurant")
	public ResponseEntity<List<Restaurant>> getAllRestaurants() {
		return new ResponseEntity<>(service.findAllRestaurants(),HttpStatus.OK);
	}
	
	@GetMapping("/restaurant/{id}")
	public ResponseEntity<Restaurant> getRestaurantById(@PathVariable int id) throws ResourceNotFoundException {
		return new ResponseEntity<>(service.findRestaurantById(id), HttpStatus.OK);
	}
	
	
	@CrossOrigin(origins= "http://localhost:3000")
	@DeleteMapping("/restaurant/{id}")
	public ResponseEntity<Restaurant> deleteRestaurantById(@PathVariable int id) throws ResourceNotFoundException {
		return new ResponseEntity<>(service.deleteRestaurantById(id), HttpStatus.OK);
	}
	
	@CrossOrigin(origins= "http://localhost:3000")
	@PutMapping("/restaurant/{id}")
	public ResponseEntity<Restaurant> updateRestaurantById(@PathVariable int id, @RequestBody Restaurant restaurant) throws ResourceNotFoundException{
		return new ResponseEntity<>(service.updateRestaurant(id, restaurant), HttpStatus.OK);
	}
	
	@CrossOrigin(origins= "http://localhost:3000")
	@PostMapping("/restaurant")
	public ResponseEntity<Restaurant> createRestaurant(@RequestBody Restaurant restaurant) {
		return new ResponseEntity<>(service.createRestaurant(restaurant), HttpStatus.CREATED);

	}
	
	@CrossOrigin(origins="http://localhost:3000")
	@GetMapping("/restaurant/name/{name}")
	public ResponseEntity<Restaurant> getRestaurantById(@PathVariable String name) throws ResourceNotFoundException {
		return new ResponseEntity<>(service.findByRestaurantName(name), HttpStatus.OK);
	}
	
	
}



