package com.dvr.mgn.weetbis.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dvr.mgn.weetbis.Validation.Validation;
import com.dvr.mgn.weetbis.dto.DishDto;
import com.dvr.mgn.weetbis.dto.RestaurantBodyDto;
import com.dvr.mgn.weetbis.dto.RestaurantDto;
import com.dvr.mgn.weetbis.dto.RestaurantWithDishesDto;
import com.dvr.mgn.weetbis.mappers.RestaurantMap;
import com.dvr.mgn.weetbis.service.interfaces.DishInterface;
import com.dvr.mgn.weetbis.service.interfaces.RestaurantInterface;

import lombok.AllArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;

@AllArgsConstructor
@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    private RestaurantInterface restaurantInterface;
    private DishInterface dishInterface;

    @PostMapping
    public ResponseEntity<?> createRestaurant(@RequestBody RestaurantDto restaurantDto) {
        try {
            Validation.isValidRestaurantDto(restaurantDto);
            System.out.println("==========> Restaurant Name: " + restaurantDto.getName());
            System.out.println("==========> Is Kosher Restaurant: " + restaurantDto.getIsKosher());
            System.out.println("==========> Restaurant Phone: " + restaurantDto.getPhone());
            System.out.println("==========> Restaurant Tag: " + restaurantDto.getTags());
            RestaurantDto newRestaurant = restaurantInterface.createRestaurant(restaurantDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(newRestaurant);
            // return ResponseEntity.created(null).build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
            
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getRestaurantById(@PathVariable("id") int restaurant_id) {
        try {
            RestaurantDto restaurantDto = restaurantInterface.getRestaurantById(restaurant_id);
            List<DishDto> dishes = dishInterface.getAllDishesByRestaurantId(restaurant_id);
            RestaurantWithDishesDto restaurantWithDishesDto = RestaurantMap.mapToRestaurantWithDishesDto(restaurantDto, dishes);
            return ResponseEntity.ok(restaurantWithDishesDto);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllRestaurants() {
        try {
            List<RestaurantDto> restaurantsDto = restaurantInterface.getAllRestaurants();
            return ResponseEntity.ok(RestaurantMap.toRestaurantBodyDtoList(restaurantsDto));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping(params = "tag")
    public ResponseEntity<List<RestaurantBodyDto>> getRestaurantsByTag(@RequestParam("tag") String tag) {
        List<RestaurantDto> restaurants = restaurantInterface.getRestaurantsByTag(tag);
        return ResponseEntity.ok(RestaurantMap.toRestaurantBodyDtoList(restaurants));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteRestaurant(@PathVariable("id") int restaurant_id) {
        try {
            restaurantInterface.deleteRestaurant(restaurant_id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateRestaurant(@PathVariable("id") int restaurant_id, @RequestBody RestaurantDto updatedRestaurantDto) {
        try {
            Validation.isEmptyTagsList(updatedRestaurantDto.getTags());
            updatedRestaurantDto.setId(restaurant_id);
            RestaurantDto updatedRestaurant = restaurantInterface.updateRestaurant(updatedRestaurantDto);
            return ResponseEntity.ok(RestaurantMap.mapToRestaurantBodyDto(updatedRestaurant));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    
}
