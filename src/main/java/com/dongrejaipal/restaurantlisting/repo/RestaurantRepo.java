package com.dongrejaipal.restaurantlisting.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dongrejaipal.restaurantlisting.entity.Restaurant;

public interface RestaurantRepo extends JpaRepository<Restaurant, Integer> {

}
