package org.delivery.kiwieats.service.order;

import lombok.RequiredArgsConstructor;
import org.delivery.kiwieats.entities.order.Order;
import org.delivery.kiwieats.repositories.order.OrderRepository;
import org.delivery.kiwieats.repositories.product.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;


@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    @Override
    public List<Order> listOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Order> getOrderById(Long orderId) {
        return Optional.of(orderRepository.findById(orderId))
                .orElse(null);
    }

    @Override
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Optional<Order> updateOrderById(Long orderId, Order order) {

        AtomicReference<Optional<Order>> atomicReference = new AtomicReference<>();

        orderRepository.findById(orderId).ifPresentOrElse(orderFound -> {

            orderFound.setOrderDetails(order.getOrderDetails());
            orderFound.setCustomer(order.getCustomer());
            orderFound.setActive(order.getActive());
            orderFound.setProducts(order.getProducts());
            orderFound.setRider(order.getRider());
            orderFound.setQuantity(order.getQuantity());
            orderFound.setOrderedDate(order.getOrderedDate());

            atomicReference.set(Optional.of(orderRepository.save(orderFound)));

        }, () -> {
            atomicReference.set(Optional.empty());
        });

        return Optional.empty();
    }

    @Override
    public Boolean deleteOrderById(Long orderId) {
        if (orderRepository.existsById(orderId)) {
            orderRepository.deleteById(orderId);
            return true;
        }
        return false;
    }
}
