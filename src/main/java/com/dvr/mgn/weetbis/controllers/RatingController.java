package com.dvr.mgn.weetbis.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dvr.mgn.weetbis.Validation.Validation;
import com.dvr.mgn.weetbis.dto.RatingDto;
import com.dvr.mgn.weetbis.service.interfaces.RatingInterface;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/ratings")
public class RatingController {
    private RatingInterface ratingInterface;

    @PostMapping
    public ResponseEntity<?> createRating(@RequestBody RatingDto ratingDto) {
        try {
            // Check if restaurant-ID exists
            int restaurantId = ratingDto.getRestaurantId();
            boolean isRestaurantIdExist = ratingInterface.isRestaurantIdExist(restaurantId);
            Validation.isValidRestaurantId(isRestaurantIdExist);
            // Check if rating is valid
            Validation.isValidRating(ratingDto.getRating());
            // Create rating
            ratingInterface.rateRestaurant(ratingDto);
            return ResponseEntity.status(201).body(null);
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
    
}
