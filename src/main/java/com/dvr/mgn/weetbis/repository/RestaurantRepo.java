package com.dvr.mgn.weetbis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dvr.mgn.weetbis.entities.Restaurant;

public interface RestaurantRepo extends JpaRepository<Restaurant, Integer> {
    boolean existsById(int id);

    // get all restaurants by tag from restaurant.tags
    @Query("SELECT r FROM Restaurant r WHERE :tag IN r.tags")
    List<Restaurant> findByTag(String tag);
}
