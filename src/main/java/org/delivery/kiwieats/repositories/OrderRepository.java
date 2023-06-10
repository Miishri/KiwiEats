package org.delivery.kiwieats.repositories;

import org.delivery.kiwieats.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
