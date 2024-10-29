package com.dvr.mgn.weetbis.mappers;

import java.util.List;
import java.util.stream.Collectors;

import com.dvr.mgn.weetbis.dto.DishDto;
import com.dvr.mgn.weetbis.entities.Dish;
import com.dvr.mgn.weetbis.dto.DishBodyDto;

public class DishMap {

    // Dish DTO -> DishBody DTO
    public static DishBodyDto mapDishDtoToDishBodyDto(DishDto dishDto) {
        DishBodyDto dishBodyDto = new DishBodyDto();
        dishBodyDto.setId(dishDto.getId());
        dishBodyDto.setName(dishDto.getName());
        dishBodyDto.setPrice(dishDto.getPrice());
        dishBodyDto.setDescription(dishDto.getDescription());
        return dishBodyDto;
    }

    // Dish DTO -> Dish
    public static Dish mapNewDishToDishDto(DishDto dishDto) {
        DishDto newDish = new DishDto();
        newDish.setName(dishDto.getName());
        newDish.setPrice(dishDto.getPrice());
        newDish.setDescription(dishDto.getDescription());
        return null;
    }

    // Dish -> Dish DTO
    public static DishDto mapDishToDishDto(Dish savedDish) {
        DishDto dishDto = new DishDto();
        dishDto.setId(savedDish.getId());
        dishDto.setName(savedDish.getName());
        dishDto.setPrice(savedDish.getPrice());
        dishDto.setDescription(savedDish.getDescription());
        return dishDto;
    }

    // Dish -> DishBody DTO
    public static DishBodyDto mapDishToDishBodyDto(Dish dish) {
        DishBodyDto dishBodyDto = new DishBodyDto();
        dishBodyDto.setId(dish.getId());
        dishBodyDto.setName(dish.getName());
        dishBodyDto.setPrice(dish.getPrice());
        dishBodyDto.setDescription(dish.getDescription());
        return dishBodyDto;
    }

    // Dish DTO -> Dish
    public static Dish mapDishDtoToDish(DishDto dishDto) {
        Dish dish = new Dish();
        dish.setId(dishDto.getId());
        dish.setName(dishDto.getName());
        dish.setPrice(dishDto.getPrice());
        dish.setDescription(dishDto.getDescription());
        return dish;
    }

    // Dish DTO List -> DishBody DTO List
    public static List<DishBodyDto> mapDishDtoListToDishBodyDtoList(List<DishDto> dishDtos) {
        return dishDtos.stream().map(DishMap::mapDishDtoToDishBodyDto).collect(Collectors.toList());
    }

    // Dish List -> Dish DTO List
    public static List<DishDto> mapDishListToDishDtoList(List<Dish> dishesList) {
        return dishesList.stream().map(DishMap::mapDishToDishDto).collect(Collectors.toList());
    }

    // Dish List -> DishBody DTO List
    public static List<DishBodyDto> mapDishListToDishBodyDtoList(List<Dish> dishesList) {
        return dishesList.stream().map(DishMap::mapDishToDishBodyDto).collect(Collectors.toList());
    }

    // update Dish
    public static void updateDish(Dish dish, DishDto updateDishDto) {
        if (updateDishDto.getName() != null)        dish.setName(updateDishDto.getName());
        if (updateDishDto.getPrice() != 0)          dish.setPrice(updateDishDto.getPrice());
        if (updateDishDto.getDescription() != null) dish.setDescription(updateDishDto.getDescription());
    }

}
