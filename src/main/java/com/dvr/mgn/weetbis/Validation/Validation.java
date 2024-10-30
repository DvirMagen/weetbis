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
        isValidRestaurantName(restaurantDto.getName());
        isValidRestaurantPhone(restaurantDto.getPhone());
        isValidKosher(restaurantDto.getIsKosher());
        isEmptyTagsList(restaurantDto.getTags());
    }

    public static void isValidRestaurantId(boolean exists) {
        if (!exists) {
            throw new InvalidRequestException("The restaurant Id does not exists.");
        }
    }

    public static void isValidRestaurantName(String name) {
        if (name == null || name.isEmpty()) {
            throw new InvalidRequestException("The restaurant name cannot be empty.");
        }
    }

    public static void isValidRestaurantPhone(String phone) {
        if (phone == null || phone.isEmpty()) {
            throw new InvalidRequestException("The restaurant phone cannot be empty.");
        }

        // Check that the phone number contains only digits, spaces, or dashes
        if (!phone.matches("^[0-9\\-\\s]+$")) {
            throw new InvalidRequestException("Invalid phone number format. It should contain only digits, spaces, or dashes.");
        }
        // Check that the phone number length is reasonable
        int length = phone.replaceAll("[^0-9]", "").length(); // Remove non-numeric characters to get actual length
        if (length < 10 || length > 15) {
            throw new InvalidRequestException("Invalid phone number length. It must be between 10 and 15 digits.");
        }
    }

    public static void isValidKosher(Boolean isKosher) {
        if (isKosher == null) {
            throw new InvalidRequestException("isKosher value is Empty");
        }
    }

    public static void isEmptyTagsList(List<String> tags) {
        if (tags.isEmpty()) {
            throw new InvalidRequestException("tags must contain at least one tag.");
        }
    }

    //Dish Check:

        public static void isValidDish(DishDto dishDto) {
        isValidDishName(dishDto.getName());
        isValidDishDescription(dishDto.getDescription());
    }

    public static void isValidDishName(String name) {
        if (name == null || name.isEmpty()) {
            throw new InvalidRequestException("The dish name cannot be empty.");
        }
    }

    public static void isValidDishDescription(String description) {
        if (description == null || description.isEmpty()) {
            throw new InvalidRequestException("The dish name cannot be empty.");
        }
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
