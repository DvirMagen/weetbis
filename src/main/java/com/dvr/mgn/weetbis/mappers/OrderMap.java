package com.dvr.mgn.weetbis.mappers;

import com.dvr.mgn.weetbis.dto.OrderDto;
import com.dvr.mgn.weetbis.entities.Order;

public class OrderMap {
    
    // Order -> OrderDto
    public static OrderDto mapOrderToOrderDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setOrderId(order.getOrderId());
        orderDto.setRestaurantId(order.getRestaurantId());
        orderDto.setOrderItems(OrderItemMap.mapOrderItemsToOrderItemDtos(order.getOrderItems()));
        return orderDto;
    }

    // OrderDto -> Order
    public static Order mapOrderDtoToOrder(OrderDto orderDto) {
        Order order = new Order();
        order.setOrderId(orderDto.getOrderId());
        order.setRestaurantId(orderDto.getRestaurantId());
        order.setOrderItems(OrderItemMap.mapOrderItemDtosToOrderItems(orderDto.getOrderItems()));
        return order;
    }
}
