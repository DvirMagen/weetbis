package com.dvr.mgn.weetbis.service.implement;

import org.springframework.stereotype.Service;

import java.util.List;

import com.dvr.mgn.weetbis.dto.OrderDto;
import com.dvr.mgn.weetbis.entities.Order;
import com.dvr.mgn.weetbis.mappers.OrderMap;
import com.dvr.mgn.weetbis.repository.DishRepo;
import com.dvr.mgn.weetbis.repository.OrderRepo;
import com.dvr.mgn.weetbis.repository.RestaurantRepo;
import com.dvr.mgn.weetbis.service.interfaces.OrderInterface;
import com.dvr.mgn.weetbis.entities.Dish;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OrderImp implements OrderInterface {

    private OrderRepo orderRepository;
    private RestaurantRepo restaurantRepository;
    private DishRepo dishRepository;

    @Override
    public OrderDto createOrder(OrderDto orderDto) {
        Order order = OrderMap.mapOrderDtoToOrder(orderDto);
        List<Dish> dishes = dishRepository.findDishesByRestaurantId(order.getRestaurantId());
        for (Dish dish : dishes) {
            if (orderDto.getOrderItems().stream().noneMatch(orderItemDto -> orderItemDto.getDishId() == dish.getId())) {
                throw new RuntimeException("Dish with id " + dish.getId() + " is not valid in the order");
            }
        }
        Order savedOrder = orderRepository.save(order);
        return OrderMap.mapOrderToOrderDto(savedOrder);
    }

    public boolean isRestaurantIdExist(int restaurantId) {
        return restaurantRepository.existsById(restaurantId);
    }
}
