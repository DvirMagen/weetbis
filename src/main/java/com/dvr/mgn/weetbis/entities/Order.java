package com.dvr.mgn.weetbis.entities;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @Id
    private String orderId;

    @Column(name = "restaurant_id")
    private int restaurantId;

    @OneToMany(mappedBy = "orderId", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;

    @PrePersist
    private void generateOrderId() {
        this.setOrderId(UUID.randomUUID().toString()); // Generate a random UUID as order ID
    }
    
}
