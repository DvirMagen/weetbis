package com.dvr.mgn.weetbis.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dvr.mgn.weetbis.dto.DishBodyDto;
import com.dvr.mgn.weetbis.dto.DishDto;
import com.dvr.mgn.weetbis.mappers.DishMap;
import com.dvr.mgn.weetbis.service.interfaces.DishInterface;

import lombok.AllArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

@AllArgsConstructor
@RestController
@RequestMapping("/restaurants/{restaurantId}/dishes")
public class DishController {
    private DishInterface dishInterface;

    @PostMapping
    public ResponseEntity<?> createDish(@PathVariable("restaurantId") int restaurantId, @RequestBody DishDto dishDto) {
        try {
            dishDto.setRestaurantId(restaurantId);
            DishDto newDish = dishInterface.createDish(dishDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(newDish);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllDishesByRestaurantId(@PathVariable("restaurantId") int restaurantId) {
        try {
            List<DishDto> dishes = dishInterface.getAllDishesByRestaurantId(restaurantId);
            List<DishBodyDto> dishesBodyList = DishMap.mapDishDtoListToDishBodyDtoList(dishes);
            return ResponseEntity.status(HttpStatus.OK).body(dishesBodyList);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDishById(@PathVariable("restaurantId") int restaurantId, @PathVariable("id") int id) {
        try {
            DishBodyDto dish = dishInterface.getDishById(restaurantId, id);
            return ResponseEntity.status(HttpStatus.OK).body(dish);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    

    @PutMapping("/{id}")
    public ResponseEntity<?> updateDish(@PathVariable("restaurantId") int restaurantId, @PathVariable int id, @RequestBody DishDto dishDto) {
        try {
            dishDto.setId(id);
            dishDto.setRestaurantId(restaurantId);
            DishDto updatedDish = dishInterface.updateDish(dishDto);
            return ResponseEntity.status(HttpStatus.OK).body(updatedDish);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDish(@PathVariable("restaurantId") int restaurantId, @PathVariable int id) {
        try {
            dishInterface.deleteDish(restaurantId, id);
            return ResponseEntity.status(HttpStatus.OK).body("Dish deleted successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
