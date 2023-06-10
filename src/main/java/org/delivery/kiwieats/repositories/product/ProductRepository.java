package org.delivery.kiwieats.repositories.product;

import org.delivery.kiwieats.entities.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
