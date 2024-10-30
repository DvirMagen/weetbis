package com.dvr.mgn.weetbis.service.implement;

import org.springframework.stereotype.Service;
import java.text.DecimalFormat;

import com.dvr.mgn.weetbis.dto.RatingDto;
import com.dvr.mgn.weetbis.dto.RestaurantDto;
import com.dvr.mgn.weetbis.entities.Restaurant;
import com.dvr.mgn.weetbis.exceptions.ResourceNotFoundException;
import com.dvr.mgn.weetbis.mappers.RestaurantMap;
import com.dvr.mgn.weetbis.repository.RestaurantRepo;
import com.dvr.mgn.weetbis.service.interfaces.RatingInterface;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RatingImp implements RatingInterface {

    private RestaurantRepo restaurantRepository;

    @Override
    public RestaurantDto rateRestaurant(RatingDto ratingDto) {
        // Check if the restaurant ID is valid
        if (!isRestaurantIdExist(ratingDto.getRestaurantId())) {
            throw new ResourceNotFoundException("Restaurant ID " + ratingDto.getRestaurantId() + " not found");
        }

        // Get the restaurant by its ID
        Restaurant restaurant = restaurantRepository.findById(ratingDto.getRestaurantId()).get();

        // Calculate the new rating
        int totalRaters = restaurant.getTotalRaters();
        double totalRating = restaurant.getAverageRating() * totalRaters;
        restaurant.setAverageRating(++totalRaters);
        double newAvgRating = (totalRating + ratingDto.getRating()) / totalRaters;

        DecimalFormat df = new DecimalFormat("0.00");
        newAvgRating = Double.parseDouble(df.format(newAvgRating));

        // Update the restaurant rating
        restaurant.setAverageRating(newAvgRating);

        // Save the updated restaurant
        Restaurant savedRestaurant = restaurantRepository.save(restaurant);

        return RestaurantMap.mapRestaurantDto(savedRestaurant);
    }

    @Override
    public boolean isRestaurantIdExist(int id) {
        return restaurantRepository.existsById(id);
    }
    
}
