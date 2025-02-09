package com.dongrejaipal.restaurantlisting.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.dongrejaipal.restaurantlisting.dto.RestaurantDTO;
import com.dongrejaipal.restaurantlisting.entity.Restaurant;
import com.dongrejaipal.restaurantlisting.mapper.RestaurantMapper;
import com.dongrejaipal.restaurantlisting.repo.RestaurantRepo;

public class RestaurantServiceTest {

	@Mock
	RestaurantRepo restaurantRepo;

	@InjectMocks
	RestaurantService restaurantService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testFindAllRestaurants() {
		// create mock data
		List<Restaurant> mockRestaurants = Arrays.asList(
				new Restaurant(1, "Paradise", "begumpet", "Hyderabad", "birayani restaurant"),
				new Restaurant(2, "Maridian", "begumpet", "Hyderabad", "birayani restaurant"));

		when(restaurantRepo.findAll()).thenReturn(mockRestaurants);

		// call the restaurant service
		List<RestaurantDTO> restaurantDTOList = restaurantService.findAllRestaurants();

		for (int i = 0; i < restaurantDTOList.size(); i++) {

			RestaurantDTO restaurantDTO = RestaurantMapper.INSTANCE
					.mapRestaurantTORestaurantDTO(mockRestaurants.get(i));

			assertEquals(restaurantDTO, restaurantDTOList.get(i));

		}

		verify(restaurantRepo, times(1)).findAll();

	}

	@Test
	public void testAddRestaurantInDB() {
		// create mock data
		RestaurantDTO restaurantDTO = new RestaurantDTO(1, "Paradise", "begumpet", "Hyderabad", "birayani restaurant");
		Restaurant restaurant = RestaurantMapper.INSTANCE.mapRestaurantDTOToResturant(restaurantDTO);

		// mock reposity behaviour
		when(restaurantRepo.save(restaurant)).thenReturn(restaurant);

		// call the restaurant service
		RestaurantDTO response = restaurantService.addRestaurantInDB(restaurantDTO);

		// verfiy resutls
		assertEquals(response, restaurantDTO);

		// call the service only once
		verify(restaurantRepo, times(1)).save(restaurant);

	}

	@Test
	public void testFindRestaurantById() {
		// mock the data
		int id = 1;
		Restaurant restaurant = new Restaurant(id, "Paradise", "begumpet", "Hyderabad", "birayani restaurant");

		// mock the repo behaviour
		when(restaurantRepo.findById(id)).thenReturn(Optional.of(restaurant));

		// call the service
		ResponseEntity<RestaurantDTO> response = restaurantService.findRestaurantById(id);

		// verify
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(restaurant.getId(), response.getBody().getId());

		// call the service or repo method at once only
		verify(restaurantRepo, times(1)).findById(id);

	}

	@Test
	public void testFindRestaurantById_withNonExistingId() {
		// mock the data
		Integer id = 1;

		// mock the repo behaviour
		when(restaurantRepo.findById(id)).thenReturn(Optional.empty());

		// call the service
		ResponseEntity<RestaurantDTO> response = restaurantService.findRestaurantById(id);

		// verify
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertEquals(null, response.getBody());

		// call the service or repo method at once only
		verify(restaurantRepo, times(1)).findById(id);

	}
}
