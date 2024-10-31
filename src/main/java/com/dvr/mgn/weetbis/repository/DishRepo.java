package com.dvr.mgn.weetbis.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dvr.mgn.weetbis.entities.Dish;

public interface DishRepo extends JpaRepository<Dish, Integer> {
    List<Dish> findDishesByRestaurantId(int restaurantId);

    @Query("SELECT d FROM Dish d WHERE d.id = :id AND d.restaurant.id = :restaurantId")
    Optional<Dish> findByIdAndRestaurantId(int id, int restaurantId);
}
