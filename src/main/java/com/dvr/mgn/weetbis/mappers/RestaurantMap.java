package com.dvr.mgn.weetbis.mappers;

import java.util.List;
import java.util.stream.Collectors;

import com.dvr.mgn.weetbis.dto.RestaurantBodyDto;
import com.dvr.mgn.weetbis.dto.RestaurantDto;
import com.dvr.mgn.weetbis.dto.RestaurantWithDishesDto;
import com.dvr.mgn.weetbis.Utils.Utils;
import com.dvr.mgn.weetbis.Validation.Validation;
import com.dvr.mgn.weetbis.dto.DishDto;
import com.dvr.mgn.weetbis.entities.Restaurant;

public class RestaurantMap {

    public static RestaurantDto mapRestaurantDto(Restaurant restaurant) {
        RestaurantDto restaurantDto = new RestaurantDto();
        restaurantDto.setId(restaurant.getId());
        restaurantDto.setName(restaurant.getName());
        restaurantDto.setPhone(restaurant.getPhone());
        restaurantDto.setIsKosher(restaurant.getIsKosher());
        restaurantDto.setTotalRaters(restaurant.getTotalRaters());
        restaurantDto.setAverageRating(restaurant.getAverageRating());
        restaurantDto.setTags(tagsFormat(restaurant.getTags()));
        return restaurantDto;
    }

    public static List<RestaurantDto> toRestaurantDtoList(List<Restaurant> restaurants) {
        return restaurants.stream().map(RestaurantMap::mapRestaurantDto).collect(Collectors.toList());
    }

    public static Restaurant mapRestaurant(RestaurantDto restaurantDto) {
        Restaurant restaurant = new Restaurant();
        restaurant.setId(restaurantDto.getId());
        restaurant.setName(restaurantDto.getName());
        restaurant.setPhone(restaurantDto.getPhone());
        restaurant.setIsKosher(restaurantDto.getIsKosher());
        restaurant.setTotalRaters(restaurantDto.getTotalRaters());
        restaurant.setAverageRating(restaurantDto.getAverageRating());
        restaurant.setTags(tagsFormat(restaurantDto.getTags()));
        return restaurant;
    }
    
    public static Restaurant mapNewRestaurant(RestaurantDto restaurantDto) {
        Restaurant restaurant = new Restaurant();
        restaurant.setId(0);
        restaurant.setName(restaurantDto.getName());
        restaurant.setPhone(restaurantDto.getPhone());
        restaurant.setIsKosher(restaurantDto.getIsKosher());
        restaurant.setTotalRaters(0);
        restaurant.setAverageRating(0);
        restaurant.setTags(tagsFormat(restaurantDto.getTags()));
        return restaurant;
    }

    public static RestaurantBodyDto mapToRestaurantBodyDto(RestaurantDto restaurantDto) {
        RestaurantBodyDto restaurantBodyDto = new RestaurantBodyDto();
        restaurantBodyDto.setId(restaurantDto.getId());
        restaurantBodyDto.setName(restaurantDto.getName());
        restaurantBodyDto.setPhone(restaurantDto.getPhone());
        restaurantBodyDto.setIsKosher(restaurantDto.getIsKosher());
        restaurantBodyDto.setAverageRating(restaurantDto.getAverageRating());
        restaurantBodyDto.setTags(tagsFormat(restaurantDto.getTags()));
        return restaurantBodyDto;
    }

    public static List<RestaurantBodyDto> toRestaurantBodyDtoList(List<RestaurantDto> restaurantDtos) {
        return restaurantDtos.stream().map(RestaurantMap::mapToRestaurantBodyDto).collect(Collectors.toList());
    }

    public static RestaurantWithDishesDto mapToRestaurantWithDishesDto(RestaurantDto restaurantDto, List<DishDto> dishes) {
        RestaurantWithDishesDto restaurantWithDishesDto = new RestaurantWithDishesDto();
        restaurantWithDishesDto.setId(restaurantDto.getId());
        restaurantWithDishesDto.setName(restaurantDto.getName());
        restaurantWithDishesDto.setPhone(restaurantDto.getPhone());
        restaurantWithDishesDto.setIsKosher(restaurantDto.getIsKosher());
        restaurantWithDishesDto.setAverageRating(restaurantDto.getAverageRating());
        restaurantWithDishesDto.setTags(tagsFormat(restaurantDto.getTags()));
        restaurantWithDishesDto.setDishes(DishMap.mapDishDtoListToDishBodyDtoList(dishes));
        return restaurantWithDishesDto;
    }

    public static void updateRestaurant(Restaurant restaurant, RestaurantDto updatedRestaurantDto) {
        if (updatedRestaurantDto.getName() != null) restaurant.setName(updatedRestaurantDto.getName());
        if (updatedRestaurantDto.getPhone() != null) {
            Validation.isValidRestaurantPhone(updatedRestaurantDto.getPhone());
            restaurant.setPhone(updatedRestaurantDto.getPhone());
        }
        if (updatedRestaurantDto.getIsKosher() != null) restaurant.setIsKosher(updatedRestaurantDto.getIsKosher());
        if (updatedRestaurantDto.getTags() != null) {
            restaurant.setTags(tagsFormat(updatedRestaurantDto.getTags()));
        }
    }

    public static List<String> tagsFormat(List<String> tags) {
        if (tags == null) return null;
        return tags.stream().map(Utils::toTitleMode).collect(Collectors.toList());
    }

}
