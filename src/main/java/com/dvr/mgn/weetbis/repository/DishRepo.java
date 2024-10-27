package com.dvr.mgn.weetbis.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.dvr.mgn.weetbis.entities.Dish;

public interface DishRepo extends JpaRepository<Dish, Integer> {
    List<Dish> findDishesByRestaurantId(int restaurantId);
}
