package org.delivery.KiwiEats.services;

import org.delivery.KiwiEats.entities.Product;

public interface ProductService {
    Product createProduct(Product product);
    Product deleteProductById(Long id);
}
