package com.dvr.mgn.weetbis.Validation;

import java.util.List;

import com.dvr.mgn.weetbis.entities.Dish;
import com.dvr.mgn.weetbis.dto.DishDto;
import com.dvr.mgn.weetbis.dto.RestaurantDto;
import com.dvr.mgn.weetbis.exceptions.InvalidRequestException;

public class Validation {

    public static void isValidString(String s) {
        if (s == null || s.isEmpty()) {
            throw new InvalidRequestException("String cannot be empty.");
        }
    }

    //Restaurant Check:

    public static void isValidRestaurantDto(RestaurantDto restaurantDto) {
        isValidString(restaurantDto.getName());
        isEmptyTagsList(restaurantDto.getTags());
    }

    public static void isValidRestaurantId(boolean exists) {
        if (!exists) {
            throw new InvalidRequestException("The restaurant Id does not exists.");
        }
    }

    public static void isEmptyTagsList(List<String> tags) {
        if (tags.isEmpty()) {
            throw new InvalidRequestException("tags must contain at least one tag.");
        }
    }

    //Dish Check:

        public static void isValidDish(DishDto dishDto) {
        isValidString(dishDto.getName());
        isValidString(dishDto.getDescription());
    }

    //Rating Check:

    public static void isValidRating(double rating) {
        if (rating < 0 || rating > 5) {
            throw new InvalidRequestException("Invalid 'rating' value. It must be between 0 and 5.");
        }
    }

    //OrderItem Check: 

        public static void isValidOrderItem(int dishId, List<Dish> dishList, int quantity) {
        isValidDishId(dishId, dishList);
        isValidQuantity(quantity);
    }

    public static void isValidDishId(int dishId, List<Dish> dishList) {
        if (dishList.stream().noneMatch(dish -> dish.getId() == dishId)) {
            throw new InvalidRequestException("Dish with id " + dishId + " is not valid in the order");
        }
    }

    public static void isValidQuantity(int quantity) {
        if (quantity < 0) {
            throw new InvalidRequestException("Invalid 'quantity' value. It must be greater or equal to 0.");
        }
    }
    
}
