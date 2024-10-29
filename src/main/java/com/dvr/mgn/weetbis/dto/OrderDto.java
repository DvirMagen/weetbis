package com.dvr.mgn.weetbis.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private String orderId;
    private int restaurantId;
    List<OrderItemDto> orderItems;
}
