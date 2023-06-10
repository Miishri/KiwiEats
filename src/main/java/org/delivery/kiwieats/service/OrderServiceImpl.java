package org.delivery.kiwieats.service;

import org.delivery.kiwieats.entities.Order;

import java.util.List;
import java.util.Optional;

public class OrderServiceImpl implements OrderService {

    @Override
    public List<Order> listOrders() {
        return null;
    }

    @Override
    public Optional<Order> getOrderById() {
        return Optional.empty();
    }

    @Override
    public Order saveOrder(Order order) {
        return null;
    }

    @Override
    public Optional<Order> updateOrderById(Long orderId, Order order) {
        return Optional.empty();
    }

    @Override
    public Boolean deleteOrderById(Long orderId) {
        return null;
    }
}
