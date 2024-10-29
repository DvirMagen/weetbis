package com.dvr.mgn.weetbis.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dvr.mgn.weetbis.entities.OrderItem;

public interface OrderItemRepo extends JpaRepository<OrderItem, Integer> {
}
