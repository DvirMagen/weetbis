package com.dvr.mgn.weetbis.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantWithDishesDto {
    private int id;
    private String name;
    private String phone;
    private Boolean isKosher;
    private double averageRating;
    private List<String> tags;
    private List<DishBodyDto> dishes;
}
