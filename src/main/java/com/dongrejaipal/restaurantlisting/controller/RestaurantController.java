package com.dongrejaipal.restaurantlisting.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dongrejaipal.restaurantlisting.dto.RestaurantDTO;
import com.dongrejaipal.restaurantlisting.service.RestaurantService;

@RestController
@RequestMapping("/restaurant")
@CrossOrigin
public class RestaurantController {

	@Autowired
	RestaurantService restaurantService;

	@GetMapping("/fetchAllRestaurant")
	public ResponseEntity<List<RestaurantDTO>> fetchAllRestaurants() {
		List<RestaurantDTO> allRestaurants = restaurantService.findAllRestaurants();
		return new ResponseEntity<>(allRestaurants, org.springframework.http.HttpStatus.OK);
	}

	@PostMapping("/addRestaurant")
	public ResponseEntity<RestaurantDTO> saveRestaurants(@RequestBody RestaurantDTO restaurantDTO) {
		RestaurantDTO restaurantAdded = restaurantService.addRestaurantInDB(restaurantDTO);
		return new ResponseEntity<>(restaurantAdded, org.springframework.http.HttpStatus.CREATED);
	}

	@GetMapping("/fetchById/{id}")
	public ResponseEntity<RestaurantDTO> findRestaurantById(@PathVariable Integer id) {
		return restaurantService.findRestaurantById(id);
	}

}
