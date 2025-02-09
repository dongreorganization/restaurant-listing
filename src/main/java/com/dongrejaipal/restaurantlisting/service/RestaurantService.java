package com.dongrejaipal.restaurantlisting.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dongrejaipal.restaurantlisting.dto.RestaurantDTO;
import com.dongrejaipal.restaurantlisting.entity.Restaurant;
import com.dongrejaipal.restaurantlisting.mapper.RestaurantMapper;
import com.dongrejaipal.restaurantlisting.repo.RestaurantRepo;

@Service
public class RestaurantService {

	@Autowired
	RestaurantRepo repo;

	public List<RestaurantDTO> findAllRestaurants() {
		// TODO Auto-generated method stub
		List<Restaurant> listRestaurant = repo.findAll();

		List<RestaurantDTO> listRestaurantDTO = listRestaurant.stream()
				.map(restaurant -> RestaurantMapper.INSTANCE.mapRestaurantTORestaurantDTO(restaurant))
				.collect(Collectors.toList());

		return listRestaurantDTO;
	}

	public RestaurantDTO addRestaurantInDB(RestaurantDTO restaurantDTO) {
		// TODO Auto-generated method stub
		Restaurant restaurant = repo.save(RestaurantMapper.INSTANCE.mapRestaurantDTOToResturant(restaurantDTO));

		return RestaurantMapper.INSTANCE.mapRestaurantTORestaurantDTO(restaurant);
	}

	public ResponseEntity<RestaurantDTO> findRestaurantById(Integer id) {
		// TODO Auto-generated method stub

		Optional<Restaurant> restuarant = repo.findById(id);

		if (restuarant.isPresent())
			return new ResponseEntity<RestaurantDTO>(
					RestaurantMapper.INSTANCE.mapRestaurantTORestaurantDTO(restuarant.get()),
					org.springframework.http.HttpStatus.OK);
		else
			return new ResponseEntity<>(null, org.springframework.http.HttpStatus.NOT_FOUND);

	}

}
