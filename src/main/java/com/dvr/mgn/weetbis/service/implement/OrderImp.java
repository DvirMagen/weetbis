package com.dvr.mgn.weetbis.service.implement;

import org.springframework.stereotype.Service;

import java.util.List;

import com.dvr.mgn.weetbis.Validation.Validation;
import com.dvr.mgn.weetbis.dto.OrderDto;
import com.dvr.mgn.weetbis.entities.Order;
import com.dvr.mgn.weetbis.entities.OrderItem;
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
        List<OrderItem> ordetItemList= order.getOrderItems();
        for (OrderItem orderItem : ordetItemList) {
            Validation.isValidOrderItem(orderItem.getDishId(), dishes, orderItem.getQuantity());
        }
        Order savedOrder = orderRepository.save(order);
        return OrderMap.mapOrderToOrderDto(savedOrder);
    }

    public boolean isRestaurantIdExist(int restaurantId) {
        return restaurantRepository.existsById(restaurantId);
    }
}
