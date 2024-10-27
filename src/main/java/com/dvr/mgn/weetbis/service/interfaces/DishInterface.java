package com.dvr.mgn.weetbis.service.interfaces;

import java.util.List;

import com.dvr.mgn.weetbis.dto.DishDto;

public interface DishInterface {

    DishDto createDish(DishDto dishDto);

    DishDto updateDish(DishDto dishDto);

    void deleteDish(int restaurantId, int id);

    List<DishDto> getAllDishesByRestaurantId(int restaurantId);

    DishDto getDishById(int restaurantId, int id);
}
