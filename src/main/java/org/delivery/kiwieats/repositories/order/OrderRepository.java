package org.delivery.kiwieats.repositories.order;

import org.delivery.kiwieats.entities.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
