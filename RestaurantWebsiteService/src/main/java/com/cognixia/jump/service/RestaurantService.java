package com.cognixia.jump.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognixia.jump.exception.ResourceNotFoundException;
import com.cognixia.jump.model.Restaurant;
import com.cognixia.jump.repository.RestaurantRepository;

@Service
public class RestaurantService {
	@Autowired
	RestaurantRepository repository;
	
	public List<Restaurant> findAllRestaurants(){
		return repository.findAll();
	}
	
	public Restaurant findRestaurantById(int id) throws ResourceNotFoundException {
		Optional<Restaurant> found = repository.findById(id);

		if (found.isEmpty()) {
			throw new ResourceNotFoundException("Restaurant with id " + id + "  not found.");
		}

		return found.get();
	}
	
	public Restaurant findByRestaurantName(String restaurantName) {
		Restaurant found = repository.findByRestaurantName(restaurantName);
		return found;
		
	}
	
	public Restaurant deleteRestaurantById(int id) throws ResourceNotFoundException {
		Restaurant deleted = findRestaurantById(id);
		repository.deleteById(id);
		return deleted;
	} 
	
	public Restaurant createRestaurant(Restaurant restaurant) {
//		restaurant.setRestaurantId(-1);
		restaurant.setId(-1);
		Restaurant created = repository.save(restaurant);
		return created;
	}
	
	public Restaurant updateRestaurant(int id, Restaurant restaurant) throws ResourceNotFoundException {
		findRestaurantById(id);
		Restaurant updated = repository.save(restaurant);
		return updated;
	}
	
	
}
