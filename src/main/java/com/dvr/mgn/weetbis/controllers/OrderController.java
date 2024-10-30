package com.dvr.mgn.weetbis.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dvr.mgn.weetbis.Validation.Validation;
import com.dvr.mgn.weetbis.dto.OrderDto;
import com.dvr.mgn.weetbis.service.interfaces.OrderInterface;

import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@AllArgsConstructor
@RestController
@RequestMapping("/order")
public class OrderController {
    private OrderInterface orderInterface;

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody OrderDto orderDto)  {
        try {
            // Check if restaurant-ID exists
            int restaurantId = orderDto.getRestaurantId();
            boolean isRestaurantIdExist = orderInterface.isRestaurantIdExist(restaurantId);
            Validation.isValidRestaurantId(isRestaurantIdExist);
            // Create order
            OrderDto savedOrder = orderInterface.createOrder(orderDto);
            String orderId = savedOrder.getOrderId();
            // Map response body to return orderId
            Map<String, Object> responseBody = new HashMap<>();
            responseBody.put("orderId", orderId);
            return ResponseEntity.ok(responseBody); // body : {"orderId": "order-1"}
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    
    
}
