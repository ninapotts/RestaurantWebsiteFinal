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
import com.cognixia.jump.model.User;
import com.cognixia.jump.service.ReviewService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ReviewController.class)
class ReviewControllerTest {
	
	private final String STARTING_URI = "http://localhost:8080/api";
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ReviewService service;
	
	@InjectMocks
	private ReviewController controller;

	@Test
	void testGetReviews() throws Exception {
		String uri = STARTING_URI + "/review";
		List<Review> reviews = Arrays.asList(
				new Review(1,"I love this movie", 4.2, new User(), new Restaurant()),
				new Review(2,"I love this movie so much", 5.0, new User(), new Restaurant()),
				new Review());
		
		when(service.findAllReviews()).thenReturn(reviews);
		
		mockMvc.perform(get(uri))
//			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
			.andExpect( jsonPath("$.length()").value(reviews.size()))
			.andExpect( jsonPath("$[0].id").value(reviews.get(0).getId()) )
			.andExpect( jsonPath("$[0].reviewContent").value(reviews.get(0).getReviewContent()) )
			.andExpect( jsonPath("$[0].rating").value(reviews.get(0).getRating()) )
			.andExpect( jsonPath("$[0].user").value(reviews.get(0).getUser()) )
			.andExpect( jsonPath("$[0].restaurant").value(reviews.get(0).getRestaurant()) )
			.andExpect( jsonPath("$[1].id").value(reviews.get(1).getId()) )
			.andExpect( jsonPath("$[1].reviewContent").value(reviews.get(1).getReviewContent()) )
			.andExpect( jsonPath("$[1].rating").value(reviews.get(1).getRating()) )
			.andExpect( jsonPath("$[1].user").value(reviews.get(1).getUser()) )
			.andExpect( jsonPath("$[1].restaurant").value(reviews.get(1).getRestaurant()) )
			.andExpect( jsonPath("$[2].id").value(reviews.get(2).getId()) )
			.andExpect( jsonPath("$[2].reviewContent").value(reviews.get(2).getReviewContent()) )
			.andExpect( jsonPath("$[2].rating").value(reviews.get(2).getRating()) )
			.andExpect( jsonPath("$[2].user").value(reviews.get(2).getUser()) )
			.andExpect( jsonPath("$[2].restaurant").value(reviews.get(2).getRestaurant()) );
	}

	@Test
	void testFindReview() throws Exception {
		
		Integer id = 1;
		Review review = new Review(id,"I love this movie", 4.2, new User(), new Restaurant());
		
		String uri = STARTING_URI + "/review/{id}";
		
		when( service.findReviewById(any(int.class)) ).thenReturn(review);
		
		mockMvc.perform(get(uri, id))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
			.andExpect( jsonPath("$.id").value(review.getId()) )
			.andExpect( jsonPath("$.reviewContent").value(review.getReviewContent()) )
			.andExpect( jsonPath("$.rating").value(review.getRating()) )
			.andExpect( jsonPath("$.user").value(review.getUser()) )
			.andExpect( jsonPath("$.restaurant").value(review.getRestaurant()) );
		
		verify( service, times(1) ).findReviewById(any(int.class));
		verifyNoMoreInteractions(service);
	}
	
	@Test
	void testReviewNotFound() throws Exception {
		Integer id = 1;
		
		String uri = STARTING_URI + "/review/{id}";

		when( service.findReviewById(any(int.class)) )
			.thenThrow(new ResourceNotFoundException("Review with id " + id + "  not found."));
		
		mockMvc.perform(get(uri, id))
//			.andDo(print())
			.andExpect(status().isNotFound());
		
		verify( service, times(1) ).findReviewById(any(int.class));
		verifyNoMoreInteractions(service);
	}
	
	@Test
	void testAddReview() throws Exception {
		
		String uri = STARTING_URI + "/review";
		Integer id = 1;
		Review review = new Review(id,"I love this movie", 4.2, new User(), new Restaurant());
		
		
		String reviewJson = review.toJson();
		
		when( service.createReview(any(Review.class)) ).thenReturn(review);
		
		mockMvc.perform(post(uri)
				.content( reviewJson )
				.contentType(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isCreated())
				.andExpect( jsonPath("$.id").value(review.getId()) )
				.andExpect( jsonPath("$.reviewContent").value(review.getReviewContent()) )
				.andExpect( jsonPath("$.rating").value(review.getRating()) )
				.andExpect( jsonPath("$.user").value(review.getUser()) )
				.andExpect( jsonPath("$.restaurant").value(review.getRestaurant()) );
		
		verify( service, times(1) ).createReview(any(Review.class));
		verifyNoMoreInteractions(service);
	}
	
	
	//Test Update
	@Test
	void testUpdate() throws Exception {
		Integer id = 1;
		Review review = new Review(id,"I love this movie", 4.2, new User(), new Restaurant());
		
		String reviewJson = review.toJson();
		
		String uri = STARTING_URI + "/review/{id}";
		
		when ( service.updateReview(any(int.class), any(Review.class)) ).thenReturn(review);
		
		mockMvc.perform(put(uri, id)
				.content( reviewJson )
				.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().is(200));
	}
	
	
	//Test Delete
	@Test
	void testDelete() throws Exception {
		Integer id = 1;
		Review review = new Review(id,"I love this movie", 4.2, new User(), new Restaurant());
		
		
		String uri = STARTING_URI + "/review/{id}";
		
		when ( service.deleteReviewById(any(int.class)) ).thenReturn(review);
		
		mockMvc.perform(delete(uri, id))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
			.andExpect( jsonPath("$.id").value(review.getId()) )
			.andExpect( jsonPath("$.reviewContent").value(review.getReviewContent()) )
			.andExpect( jsonPath("$.rating").value(review.getRating()) )
			.andExpect( jsonPath("$.user").value(review.getUser()) )
			.andExpect( jsonPath("$.restaurant").value(review.getRestaurant()) );
		
		verify( service, times(1) ).deleteReviewById(any(int.class));
		verifyNoMoreInteractions(service);
	}
}