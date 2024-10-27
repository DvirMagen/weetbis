package com.dvr.mgn.weetbis.service.interfaces;

import java.util.List;

import com.dvr.mgn.weetbis.dto.RestaurantDto;

public interface RestaurantInterface {

    RestaurantDto createRestaurant(RestaurantDto restaurantDto);

    RestaurantDto getRestaurantById(int id);

    List<RestaurantDto> getAllRestaurants();

    RestaurantDto updateRestaurant(RestaurantDto restaurantDto);

    void deleteRestaurant(int id);

    List<RestaurantDto> getRestaurantsByTag(String tag);
    
}
