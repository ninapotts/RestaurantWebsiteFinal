package com.cognixia.jump.controller;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.cognixia.jump.exception.ResourceNotFoundException;
import com.cognixia.jump.model.Restaurant;
import com.cognixia.jump.model.Review;
import com.cognixia.jump.model.UserModel;
import com.cognixia.jump.service.RestaurantService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(RestaurantController.class)
class RestaurantControllerTest {
	
	private final String STARTING_URI = "http://localhost:8080/api";
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private RestaurantService service;
	
	@InjectMocks
	private RestaurantController controller;
	
	
	@Test
	void testGetRestaurants() throws Exception {
		
		int array[] = { 1, 2, 4};
		
		
		String uri = STARTING_URI + "/restaurant";
		List<Review> reviews = Arrays.asList(
				new Review(1,"I love this movie", 4.2),
				new Review(2,"I love this movie so much", 5.0),
				new Review());
		
		
		List<Restaurant> restaurants = Arrays.asList(
				new Restaurant(1,"In and out", "123 lane", "Sells burgers", 4.5),
				new Restaurant(2,"Chik fil a", "123 lane", "Sells burgers", 4.5),
				new Restaurant());
		
		when(service.findAllRestaurants()).thenReturn(restaurants);
		
		mockMvc.perform(get(uri))
//			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
			.andExpect( jsonPath("$.length()").value(restaurants.size()))
			.andExpect( jsonPath("$[0].id").value(restaurants.get(0).getId()) )
			.andExpect( jsonPath("$[0].restaurantName").value(restaurants.get(0).getRestaurantName()) )
			.andExpect( jsonPath("$[0].restaurantAddress").value(restaurants.get(0).getRestaurantAddress()) )
			.andExpect( jsonPath("$[0].restaurantDescription").value(restaurants.get(0).getRestaurantDescription()) )
			.andExpect( jsonPath("$[0].restaurantRating").value(restaurants.get(0).getRestaurantRating()) )
			//.andExpect( jsonPath("$[0].reviews").value(restaurants.get(0).getReviews()) )
					
			.andExpect( jsonPath("$[1].id").value(restaurants.get(1).getId()) )
			.andExpect( jsonPath("$[1].restaurantName").value(restaurants.get(1).getRestaurantName()) )
			.andExpect( jsonPath("$[1].restaurantAddress").value(restaurants.get(1).getRestaurantAddress()) )
			.andExpect( jsonPath("$[1].restaurantDescription").value(restaurants.get(1).getRestaurantDescription()) )
			.andExpect( jsonPath("$[1].restaurantRating").value(restaurants.get(1).getRestaurantRating()) )
			//.andExpect( jsonPath("$[1].reviews").value(restaurants.get(1).getReviews()) )
			
			.andExpect( jsonPath("$[2].id").value(restaurants.get(2).getId()) )
			.andExpect( jsonPath("$[2].restaurantName").value(restaurants.get(2).getRestaurantName()) )
			.andExpect( jsonPath("$[2].restaurantAddress").value(restaurants.get(2).getRestaurantAddress()) )
			.andExpect( jsonPath("$[2].restaurantDescription").value(restaurants.get(2).getRestaurantDescription()) )
			.andExpect( jsonPath("$[2].restaurantRating").value(restaurants.get(2).getRestaurantRating()) 
			);

	}
	
	
	
	@Test
	void testFindRestaurant() throws Exception {
		
		List<Review> reviews = Arrays.asList(
				new Review(1,"I love this movie", 4.2),
				new Review(2,"I love this movie so much", 5.0),
				new Review());
		Integer id = 1;
		Restaurant restaurant = new Restaurant(2,"Chik fil a", "123 lane", "Sells burgers", 4.5);
		
		String uri = STARTING_URI + "/restaurant/{id}";
		
		when( service.findRestaurantById(any(int.class)) ).thenReturn(restaurant);
		mockMvc.perform(get(uri, id))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))

		.andExpect( jsonPath("$.id").value(restaurant.getId()) )
		.andExpect( jsonPath("$.restaurantName").value(restaurant.getRestaurantName()) )
		.andExpect( jsonPath("$.restaurantAddress").value(restaurant.getRestaurantAddress()) )
		.andExpect( jsonPath("$.restaurantDescription").value(restaurant.getRestaurantDescription()) )
		.andExpect( jsonPath("$.restaurantRating").value(restaurant.getRestaurantRating()) );

		
		verify( service, times(1) ).findRestaurantById(any(int.class));
		verifyNoMoreInteractions(service);
	}

	@Test
	void testRestaurantNotFound() throws Exception {
		Integer id = 1;
		
		String uri = STARTING_URI + "/restaurant/{id}";

		when( service.findRestaurantById(any(int.class)) )
			.thenThrow(new ResourceNotFoundException("Restaurant with id " + id + "  not found."));
		
		mockMvc.perform(get(uri, id))
//			.andDo(print())
			.andExpect(status().isNotFound());
		
		verify( service, times(1) ).findRestaurantById(any(int.class));
		verifyNoMoreInteractions(service);
	}
	
	
	
	

	@Test
	void testAddRestaurant() throws Exception {
		
		String uri = STARTING_URI + "/restaurant";
		List<Review> reviews = Arrays.asList(
				new Review(1,"I love this movie", 4.2/*, new User(), new Restaurant()*/),
				new Review(2,"I love this movie so much", 5.0/*, new User(), new Restaurant()*/),
				new Review());
		
		Restaurant restaurant = new Restaurant(2,"Chik fil a", "123 lane", "Sells burgers", 4.5/*, reviews*/);
		
		String restaurantJson = restaurant.toJson();
		
		when( service.createRestaurant(any(Restaurant.class)) ).thenReturn(restaurant);
		
		mockMvc.perform(post(uri)
				.content( restaurantJson )
				.contentType(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isCreated())
				.andExpect( jsonPath("$.id").value(restaurant.getId()) )
				.andExpect( jsonPath("$.restaurantName").value(restaurant.getRestaurantName()) )
				.andExpect( jsonPath("$.restaurantAddress").value(restaurant.getRestaurantAddress()) )
				.andExpect( jsonPath("$.restaurantDescription").value(restaurant.getRestaurantDescription()) )
				.andExpect( jsonPath("$.restaurantRating").value(restaurant.getRestaurantRating()) 
				//.andExpect( jsonPath("$.reviews").value(restaurant.getReviews()) 
				);
		
		verify( service, times(1) ).createRestaurant(any(Restaurant.class));
		verifyNoMoreInteractions(service);
	}
	
	
	//Test Update
	@Test
	void testUpdate() throws Exception {
		List<Review> reviews = Arrays.asList(
				new Review(1,"I love this movie", 4.2/*, new User(), new Restaurant()*/),
				new Review(2,"I love this movie so much", 5.0/*, new User(), new Restaurant()*/),
				new Review());
		Integer id = 1;
		Restaurant restaurant = new Restaurant(2,"Chik fil a", "123 lane", "Sells burgers", 4.5/*, reviews*/);
		String restaurantJson = restaurant.toJson();
		
		String uri = STARTING_URI + "/restaurant/{id}";
		
		when ( service.updateRestaurant(any(int.class), any(Restaurant.class)) ).thenReturn(restaurant);
		
		mockMvc.perform(put(uri, id)
				.content( restaurantJson )
				.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().is(200));
	}
	
	
	
	
	//Test Delete
	@Test
	void testDelete() throws Exception {
		List<Review> reviews = Arrays.asList(
				new Review(1,"I love this movie", 4.2/*, new User(), new Restaurant()*/),
				new Review(2,"I love this movie so much", 5.0/*, new User(), new Restaurant()*/),
				new Review());
		Integer id = 1;
		Restaurant restaurant = new Restaurant(2,"Chik fil a", "123 lane", "Sells burgers", 4.5/*, reviews*/);
		
		String uri = STARTING_URI + "/restaurant/{id}";
		
		when ( service.deleteRestaurantById(any(int.class)) ).thenReturn(restaurant);
		
		mockMvc.perform(delete(uri, id))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
			.andExpect( jsonPath("$.id").value(restaurant.getId()) )
			.andExpect( jsonPath("$.restaurantName").value(restaurant.getRestaurantName()) )
			.andExpect( jsonPath("$.restaurantAddress").value(restaurant.getRestaurantAddress()) )
			.andExpect( jsonPath("$.restaurantDescription").value(restaurant.getRestaurantDescription()) )
			.andExpect( jsonPath("$.restaurantRating").value(restaurant.getRestaurantRating()) )
			//.andExpect( jsonPath("$.reviews").value(restaurant.getReviews()) 
			;
		
		verify( service, times(1) ).deleteRestaurantById(any(int.class));
		verifyNoMoreInteractions(service);
	}
	
	
}

	