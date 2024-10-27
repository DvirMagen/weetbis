package com.dvr.mgn.weetbis.service.interfaces;

import com.dvr.mgn.weetbis.dto.RatingDto;
import com.dvr.mgn.weetbis.dto.RestaurantDto;

public interface RatingInterface {
    // Rate a restaurant by its id and rating
    RestaurantDto rateRestaurant(RatingDto ratingDto);

    //Check if the restaurant ID is valid
    boolean isRestaurantIdExist(int id);

}
