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
import com.cognixia.jump.model.Car;
import com.cognixia.jump.service.CarService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CarController.class)
class CarControllerTest {
	
	private final String STARTING_URI = "http://localhost:8080/api";
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private CarService service;
	
	@InjectMocks
	private CarController controller;

	@Test
	void testGetCars() throws Exception {
		String uri = STARTING_URI + "/car";
		
		List<Car> cars = Arrays.asList(
				new Car(1L,"New Car",0,"Black"),
				new Car(2L,"Next Car",5,"White"),
				new Car());
		
		when(service.findAllCars()).thenReturn(cars);
		
		mockMvc.perform(get(uri))
//			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
			.andExpect( jsonPath("$.length()").value(cars.size()))
			.andExpect( jsonPath("$[0].id").value(cars.get(0).getId()) )
			.andExpect( jsonPath("$[0].type").value(cars.get(0).getType()) )
			.andExpect( jsonPath("$[0].miles").value(cars.get(0).getMiles()) )
			.andExpect( jsonPath("$[0].color").value(cars.get(0).getColor()) )
			.andExpect( jsonPath("$[1].id").value(cars.get(1).getId()) )
			.andExpect( jsonPath("$[1].type").value(cars.get(1).getType()) )
			.andExpect( jsonPath("$[1].miles").value(cars.get(1).getMiles()) )
			.andExpect( jsonPath("$[1].color").value(cars.get(1).getColor()) )
			.andExpect( jsonPath("$[2].id").value(cars.get(2).getId()) )
			.andExpect( jsonPath("$[2].type").value(cars.get(2).getType()) )
			.andExpect( jsonPath("$[2].miles").value(cars.get(2).getMiles()) )
			.andExpect( jsonPath("$[2].color").value(cars.get(2).getColor()) );
	}

	@Test
	void testFindCar() throws Exception {
		
		long id = 1;
		Car car = new Car(id, "test car", 0, "white");
		
		String uri = STARTING_URI + "/car/{id}";
		
		when( service.findCarById(any(int.class)) ).thenReturn(car);
		
		mockMvc.perform(get(uri, id))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
			.andExpect( jsonPath("$.id").value(car.getId()))
			.andExpect( jsonPath("$.type").value(car.getType()))
			.andExpect( jsonPath("$.miles").value(car.getMiles()))
			.andExpect( jsonPath("$.color").value(car.getColor()));
		
		verify( service, times(1) ).findCarById(any(int.class));
		verifyNoMoreInteractions(service);
	}
	
	@Test
	void testCarNotFound() throws Exception {
		long id = 1;
		
		String uri = STARTING_URI + "/car/{id}";

		when( service.findCarById(any(int.class)) )
			.thenThrow(new ResourceNotFoundException("Car with id " + id + "  not found."));
		
		mockMvc.perform(get(uri, id))
//			.andDo(print())
			.andExpect(status().isNotFound());
		
		verify( service, times(1) ).findCarById(any(int.class));
		verifyNoMoreInteractions(service);
	}
	
	@Test
	void testAddCar() throws Exception {
		
		String uri = STARTING_URI + "/car";
		Car car = new Car(1l, "New Car", 0, "Black");
		
		String carJson = car.toJson();
		
		when( service.createCar(any(Car.class)) ).thenReturn(car);
		
		mockMvc.perform(post(uri)
				.content( carJson )
				.contentType(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isCreated())
				.andExpect( jsonPath("$.id").value(car.getId()))
				.andExpect( jsonPath("$.type").value(car.getType()))
				.andExpect( jsonPath("$.miles").value(car.getMiles()))
				.andExpect( jsonPath("$.color").value(car.getColor()));
		
		verify( service, times(1) ).createCar(any(Car.class));
		verifyNoMoreInteractions(service);
	}
	
	
	//Test Update
	@Test
	void testUpdate() throws Exception {
		long id = 1;
		Car car = new Car(id, "update car", 0, "white");
		String carJson = car.toJson();
		
		String uri = STARTING_URI + "/car/{id}";
		
		when ( service.updateCar(any(int.class), any(Car.class)) ).thenReturn(car);
		
		mockMvc.perform(put(uri, id)
				.content( carJson )
				.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().is(200));
	}
	
	
	//Test Delete
	@Test
	void testDelete() throws Exception {
		long id = 1;
		Car car = new Car(id, "delete car", 0, "white");
		
		String uri = STARTING_URI + "/car/{id}";
		
		when ( service.deleteCarById(any(int.class)) ).thenReturn(car);
		
		mockMvc.perform(delete(uri, id))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
			.andExpect( jsonPath("$.id").value(car.getId()))
			.andExpect( jsonPath("$.type").value(car.getType()))
			.andExpect( jsonPath("$.miles").value(car.getMiles()))
			.andExpect( jsonPath("$.color").value(car.getColor()));
		
		verify( service, times(1) ).deleteCarById(any(int.class));
		verifyNoMoreInteractions(service);
	}
}
