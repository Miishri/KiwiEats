package org.delivery.kiwieats.service;

import org.delivery.kiwieats.model.OrderDTO;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    List<OrderDTO> listOrders();
    Optional<OrderDTO> getOrderById();

    OrderDTO saveOrder(OrderDTO orderDTO);

    Optional<OrderDTO> updateOrderById(Long orderId, OrderDTO orderDTO);

    Boolean deleteOrderById(Long orderId);
}
