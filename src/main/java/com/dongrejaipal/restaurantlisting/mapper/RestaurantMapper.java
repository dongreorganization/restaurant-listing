package com.dongrejaipal.restaurantlisting.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.dongrejaipal.restaurantlisting.dto.RestaurantDTO;
import com.dongrejaipal.restaurantlisting.entity.Restaurant;

@Mapper
public interface RestaurantMapper {

	RestaurantMapper INSTANCE = Mappers.getMapper(RestaurantMapper.class);

	Restaurant mapRestaurantDTOToResturant(RestaurantDTO restaurantDTO);

	RestaurantDTO mapRestaurantTORestaurantDTO(Restaurant restaurant);
}
