package com.dongrejaipal.restaurantlisting.controller;

import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.dongrejaipal.restaurantlisting.dto.RestaurantDTO;
import com.dongrejaipal.restaurantlisting.service.RestaurantService;

public class RestaurantControllerTest {

	@InjectMocks
	RestaurantController restController;

	@Mock
	RestaurantService restaurantService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testFetchAllRestaurant() {
		// mock the service behaviour
		List<RestaurantDTO> mockRestaurants = Arrays.asList(
				new RestaurantDTO(1, "Paradise", "begumpet", "Hyderabad", "birayani restaurant"),
				new RestaurantDTO(2, "Maridian", "begumpet", "Hyderabad", "birayani restaurant"));

		when(restaurantService.findAllRestaurants()).thenReturn(mockRestaurants);

		// call the controller
		ResponseEntity<List<RestaurantDTO>> response = restController.fetchAllRestaurants();

		// verify the response
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(mockRestaurants, response.getBody());

		// call the service only once
		verify(restaurantService, times(1)).findAllRestaurants();

	}

	@Test
	public void testSaveRestaurants() {
		// mock the service behaviour
		RestaurantDTO mockRestaurant = new RestaurantDTO(2, "Maridian", "begumpet", "Hyderabad", "birayani restaurant");

		when(restaurantService.addRestaurantInDB(mockRestaurant)).thenReturn(mockRestaurant);

		// call the controller
		ResponseEntity<RestaurantDTO> response = restController.saveRestaurants(mockRestaurant);

		// verify the response
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals(mockRestaurant, response.getBody());

		// call the service only once
		verify(restaurantService, times(1)).addRestaurantInDB(mockRestaurant);

	}

}
