package org.delivery.KiwiEats.repositories;

import org.delivery.KiwiEats.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {}
