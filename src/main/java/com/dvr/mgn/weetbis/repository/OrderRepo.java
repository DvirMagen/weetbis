package com.dvr.mgn.weetbis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.dvr.mgn.weetbis.entities.Order;

public interface OrderRepo extends JpaRepository<Order, String> {
}
