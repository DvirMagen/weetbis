package com.dvr.mgn.weetbis.service.implement;

import com.dvr.mgn.weetbis.service.interfaces.RestaurantInterface;

import lombok.AllArgsConstructor;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dvr.mgn.weetbis.dto.RestaurantDto;
import com.dvr.mgn.weetbis.entities.Restaurant;
import com.dvr.mgn.weetbis.mappers.RestaurantMap;
import com.dvr.mgn.weetbis.repository.RestaurantRepo;

@Service
@AllArgsConstructor
public class RestaurantImp implements RestaurantInterface {

    private RestaurantRepo restaurantRepository;

    @Override
    public RestaurantDto createRestaurant(RestaurantDto restaurantDto) {
        Restaurant restaurant = RestaurantMap.mapNewRestaurant(restaurantDto);
        // print log restaurant
        System.out.println(restaurant);
        Restaurant savedRestaurant = restaurantRepository.save(restaurant);
        return RestaurantMap.mapRestaurantDto(savedRestaurant);
    }

    @Override
    public List<RestaurantDto> getAllRestaurants() {
        List<Restaurant> restaurantsList = restaurantRepository.findAll();
        return RestaurantMap.toRestaurantDtoList(restaurantsList);
    }

    @Override
    public RestaurantDto getRestaurantById(int id) {
        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow(() -> new RuntimeException("Restaurant not found"));
        return RestaurantMap.mapRestaurantDto(restaurant);
    }

    @Override
    public List<RestaurantDto> getRestaurantsByTag(String tag) {
        List<Restaurant> restaurantsList = restaurantRepository.findByTag(tag);
        return RestaurantMap.toRestaurantDtoList(restaurantsList);

    }

    // create new updated restaurant, 
    // update the restaurant using the updateRestaurant method,
    // save the updated restaurant
    @Override
    public RestaurantDto updateRestaurant(RestaurantDto updatedRestaurantDto) {
        Restaurant restaurant = restaurantRepository.findById(updatedRestaurantDto.getId()).orElseThrow(
            () -> new RuntimeException("Restaurant " + updatedRestaurantDto.getId() + " not found"));

        RestaurantMap.updateRestaurant(restaurant, updatedRestaurantDto);
        return RestaurantMap.mapRestaurantDto(restaurant);
    }

    @Override
    public void deleteRestaurant(int id) {
        restaurantRepository.findById(id).orElseThrow(
            () -> new RuntimeException("Restaurant " + id + " not found"));
        restaurantRepository.deleteById(id);
    }

}
