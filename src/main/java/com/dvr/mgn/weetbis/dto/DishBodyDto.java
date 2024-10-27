package com.dvr.mgn.weetbis.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DishBodyDto {
    private int id;
    private String name;
    private String description;
    private double price;
}
