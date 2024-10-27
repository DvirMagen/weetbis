package com.dvr.mgn.weetbis.mappers;

import java.util.List;
import java.util.stream.Collectors;

import com.dvr.mgn.weetbis.dto.DishDto;
import com.dvr.mgn.weetbis.dto.DishBodyDto;

public class DishMap {
    public static DishBodyDto toDishBodyDto(DishDto dishDto) {
        DishBodyDto dishBodyDto = new DishBodyDto();
        dishBodyDto.setId(dishDto.getId());
        dishBodyDto.setName(dishDto.getName());
        dishBodyDto.setPrice(dishDto.getPrice());
        dishBodyDto.setDescription(dishDto.getDescription());
        return dishBodyDto;
    }

    public static List<DishBodyDto> toDishBodyDtoList(List<DishDto> dishDtos) {
        return dishDtos.stream().map(DishMap::toDishBodyDto).collect(Collectors.toList());
    }
}
