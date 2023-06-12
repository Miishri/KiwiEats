package org.delivery.kiwieats.repositories;

import org.delivery.kiwieats.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
