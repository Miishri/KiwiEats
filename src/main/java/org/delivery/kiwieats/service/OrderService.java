package org.delivery.kiwieats.service;

import org.delivery.kiwieats.entities.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    List<Order> listOrders();
    Optional<Order> getOrderById();

    Order saveOrder(Order order);

    Optional<Order> updateOrderById(Long orderId, Order order);

    Boolean deleteOrderById(Long orderId);
}
