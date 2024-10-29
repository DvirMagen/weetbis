package com.dvr.mgn.weetbis.mappers;


import java.util.stream.Collectors;
import java.util.List;

import com.dvr.mgn.weetbis.dto.OrderItemDto;
import com.dvr.mgn.weetbis.entities.OrderItem;

public class OrderItemMap {

    // OrderItem -> OrderItemDto
    public static OrderItemDto mapOrderItemToOrderItemDto(OrderItem orderItem) {
        OrderItemDto orderItemDto = new OrderItemDto();
        orderItemDto.setId(orderItem.getId());
        orderItemDto.setOrderId(orderItem.getOrderId());
        orderItemDto.setDishId(orderItem.getDishId());
        orderItemDto.setQuantity(orderItem.getQuantity());
        return orderItemDto;
    }

    // OrderItemDto -> OrderItem
    public static OrderItem mapOrderItemDtoToOrderItem(OrderItemDto orderItemDto) {
        OrderItem orderItem = new OrderItem();
        orderItem.setId(orderItemDto.getId());
        orderItem.setOrderId(orderItemDto.getOrderId());
        orderItem.setDishId(orderItemDto.getDishId());
        orderItem.setQuantity(orderItemDto.getQuantity());
        return orderItem;
    }

    // List<OrderItem> -> List<OrderItemDto>
    public static List<OrderItemDto> mapOrderItemsToOrderItemDtos(List<OrderItem> orderItems) {
        return orderItems.stream().map(OrderItemMap::mapOrderItemToOrderItemDto).collect(Collectors.toList());
    }

    // List<OrderItemDto> -> List<OrderItem>
    public static List<OrderItem> mapOrderItemDtosToOrderItems(List<OrderItemDto> orderItemDtos) {
        return orderItemDtos.stream().map(OrderItemMap::mapOrderItemDtoToOrderItem).collect(Collectors.toList());
    }
    
}
