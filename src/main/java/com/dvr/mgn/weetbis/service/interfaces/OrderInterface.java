package com.dvr.mgn.weetbis.service.interfaces;

import com.dvr.mgn.weetbis.dto.OrderDto;

public interface OrderInterface {

    OrderDto createOrder(OrderDto orderDto);

    boolean isRestaurantIdExist(int restaurantId);
}
