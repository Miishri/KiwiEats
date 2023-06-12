package org.delivery.kiwieats.service.order;

import org.delivery.kiwieats.entities.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    List<Order> listOrders();
    Optional<Order> getOrderById(Long orderId);

    Order saveOrder(Order order);

    Optional<Order> updateOrderById(Long orderId, Order order);

    Boolean deleteOrderById(Long orderId);
}
