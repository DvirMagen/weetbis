package com.dvr.mgn.weetbis.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDto {
    private int id;
    private int dishId;
    private int quantity;
    private String orderId;
}
