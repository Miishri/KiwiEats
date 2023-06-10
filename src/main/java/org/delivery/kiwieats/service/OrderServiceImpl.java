package org.delivery.kiwieats.service;

import org.delivery.kiwieats.model.OrderDTO;

import java.util.List;
import java.util.Optional;

public class OrderServiceImpl implements OrderService {
    @Override
    public List<OrderDTO> listOrders() {
        return null;
    }

    @Override
    public Optional<OrderDTO> getOrderById() {
        return Optional.empty();
    }

    @Override
    public OrderDTO saveOrder(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public Optional<OrderDTO> updateOrderById(Long orderId, OrderDTO orderDTO) {
        return Optional.empty();
    }

    @Override
    public Boolean deleteOrderById(Long orderId) {
        return null;
    }
}
