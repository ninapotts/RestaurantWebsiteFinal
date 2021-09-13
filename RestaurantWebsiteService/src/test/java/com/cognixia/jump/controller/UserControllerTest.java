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
import com.cognixia.jump.service.UserService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
class UserControllerTest {
	
	private final String STARTING_URI = "http://localhost:8080/api";
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserService service;
	
	@InjectMocks
	private UserController controller;

	@Test
	void testGetUsers() throws Exception {
		String uri = STARTING_URI + "/user";
		List<Review> reviews = Arrays.asList(
				new Review(1,"I love this movie", 4.2/*, new User(), new Restaurant()*/),
				new Review(2,"I love this movie so much", 5.0/*, new User(), new Restaurant()*/),
				new Review());
		List<User> users = Arrays.asList(
				new User(1,"user1", "password", true/*, reviews*/),
				new User(2,"user2", "password", true/*, reviews*/),
				new User());
		
		when(service.findAllUsers()).thenReturn(users);
		
		mockMvc.perform(get(uri))
//			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
			.andExpect( jsonPath("$.length()").value(users.size()))
			.andExpect( jsonPath("$[0].id").value(users.get(0).getId()) )
			.andExpect( jsonPath("$[0].userName").value(users.get(0).getUserName()) )
			.andExpect( jsonPath("$[0].password").value(users.get(0).getPassword()) )
			.andExpect( jsonPath("$[0].isAdmin").value(users.get(0).getIsAdmin()) )
			//.andExpect( jsonPath("$[0].reviews").value(users.get(0).getReviews()) )
			.andExpect( jsonPath("$[1].id").value(users.get(1).getId()) )
			.andExpect( jsonPath("$[1].userName").value(users.get(1).getUserName()) )
			.andExpect( jsonPath("$[1].password").value(users.get(1).getPassword()) )
			.andExpect( jsonPath("$[1].isAdmin").value(users.get(1).getIsAdmin()) )
			//.andExpect( jsonPath("$[1].reviews").value(users.get(1).getReviews()) )
			.andExpect( jsonPath("$[2].id").value(users.get(2).getId()) )
			.andExpect( jsonPath("$[2].userName").value(users.get(2).getUserName()) )
			.andExpect( jsonPath("$[2].password").value(users.get(2).getPassword()) )
			.andExpect( jsonPath("$[2].isAdmin").value(users.get(2).getIsAdmin()) )
			//.andExpect( jsonPath("$[2].reviews").value(users.get(2).getReviews()) )
			;
	}

	@Test
	void testFindUser() throws Exception {
		
		List<Review> reviews = Arrays.asList(
				new Review(1,"I love this movie", 4.2/*, new User(), new Restaurant()*/),
				new Review(2,"I love this movie so much", 5.0/*, new User(), new Restaurant()*/),
				new Review());
		Integer id = 1;
		User user = new User(id,"user1", "password", true/*, reviews*/);
		
		String uri = STARTING_URI + "/user/{id}";
		
		when( service.findUserById(any(int.class)) ).thenReturn(user);
		
		mockMvc.perform(get(uri, id))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
			.andExpect( jsonPath("$.id").value(user.getId()) )
			.andExpect( jsonPath("$.userName").value(user.getUserName()) )
			.andExpect( jsonPath("$.password").value(user.getPassword()) )
			.andExpect( jsonPath("$.isAdmin").value(user.getIsAdmin()) )
			//.andExpect( jsonPath("$.reviews").value(user.getReviews()) )
			;
		
		verify( service, times(1) ).findUserById(any(int.class));
		verifyNoMoreInteractions(service);
	}
	
	@Test
	void testUserNotFound() throws Exception {
		Integer id = 1;
		
		String uri = STARTING_URI + "/user/{id}";

		when( service.findUserById(any(int.class)) )
			.thenThrow(new ResourceNotFoundException("User with id " + id + "  not found."));
		
		mockMvc.perform(get(uri, id))
//			.andDo(print())
			.andExpect(status().isNotFound());
		
		verify( service, times(1) ).findUserById(any(int.class));
		verifyNoMoreInteractions(service);
	}
	
	@Test
	void testAddUser() throws Exception {
		
		String uri = STARTING_URI + "/user";
		List<Review> reviews = Arrays.asList(
				new Review(1,"I love this movie", 4.2/*, new User(), new Restaurant()*/),
				new Review(2,"I love this movie so much", 5.0/*, new User(), new Restaurant()*/),
				new Review());
		User user = new User(1,"user1", "password", true/*, reviews*/);
		
		String userJson = user.toJson();
		
		when( service.createUser(any(User.class)) ).thenReturn(user);
		
		mockMvc.perform(post(uri)
				.content( userJson )
				.contentType(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isCreated())
				.andExpect( jsonPath("$.id").value(user.getId()) )
				.andExpect( jsonPath("$.userName").value(user.getUserName()) )
				.andExpect( jsonPath("$.password").value(user.getPassword()) )
				.andExpect( jsonPath("$.isAdmin").value(user.getIsAdmin()) )
				//.andExpect( jsonPath("$.reviews").value(user.getReviews()) )
				;
		
		verify( service, times(1) ).createUser(any(User.class));
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
		User user = new User(id,"user1", "password", true/*, reviews*/);
		String userJson = user.toJson();
		
		String uri = STARTING_URI + "/user/{id}";
		
		when ( service.updateUser(any(int.class), any(User.class)) ).thenReturn(user);
		
		mockMvc.perform(put(uri, id)
				.content( userJson )
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
		User user = new User(id,"user1", "password", true/*, reviews*/);
		
		String uri = STARTING_URI + "/user/{id}";
		
		when ( service.deleteUserById(any(int.class)) ).thenReturn(user);
		
		mockMvc.perform(delete(uri, id))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
			.andExpect( jsonPath("$.id").value(user.getId()) )
			.andExpect( jsonPath("$.userName").value(user.getUserName()) )
			.andExpect( jsonPath("$.password").value(user.getPassword()) )
			.andExpect( jsonPath("$.isAdmin").value(user.getIsAdmin()) )
			//.andExpect( jsonPath("$.reviews").value(user.getReviews()) )
			;
		
		verify( service, times(1) ).deleteUserById(any(int.class));
		verifyNoMoreInteractions(service);
	}
}
