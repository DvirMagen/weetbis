package com.dvr.mgn.weetbis.service.implement;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dvr.mgn.weetbis.dto.DishBodyDto;
import com.dvr.mgn.weetbis.dto.DishDto;
import com.dvr.mgn.weetbis.entities.Dish;
import com.dvr.mgn.weetbis.exceptions.ResourceNotFoundException;
import com.dvr.mgn.weetbis.mappers.DishMap;
import com.dvr.mgn.weetbis.repository.DishRepo;
import com.dvr.mgn.weetbis.service.interfaces.DishInterface;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DishImp implements DishInterface {

    private DishRepo dishRepository;

    @Override
    public DishDto createDish(DishDto dishDto) {
        Dish dish = DishMap.mapNewDishToDishDto(dishDto);
        Dish savedDish = dishRepository.save(dish);
        return DishMap.mapDishToDishDto(savedDish);
    }

    @Override
    public List<DishDto> getAllDishesByRestaurantId(int restaurantId) {
        List<Dish> dishesList = dishRepository.findDishesByRestaurantId(restaurantId);
        return DishMap.mapDishListToDishDtoList(dishesList);
    }

    @Override
    public DishBodyDto getDishById(int restaurant_id, int dishId) {
        Dish dish = dishRepository.findByIdAndRestaurantId(restaurant_id, dishId).orElseThrow(
            () -> new ResourceNotFoundException("Dish id " + dishId + " not found"));
        return DishMap.mapDishToDishBodyDto(dish);
    }

    @Override
    public DishDto updateDish(DishDto dishDto) {
        Dish dish = dishRepository.findByIdAndRestaurantId(dishDto.getRestaurantId(), dishDto.getId()).orElseThrow(
            () -> new ResourceNotFoundException("Dish id " + dishDto.getId() + " not found"));
        DishMap.updateDish(dish, dishDto);
        Dish savedDish = dishRepository.save(dish);
        return DishMap.mapDishToDishDto(savedDish);
    }

    @Override
    public void deleteDish(int restaurant_id, int dishId) {
        Dish dish = dishRepository.findByIdAndRestaurantId(restaurant_id, dishId).orElseThrow(
            () -> new ResourceNotFoundException("Dish id " + dishId + " not found"));
            dishRepository.delete(dish);
    }
}
