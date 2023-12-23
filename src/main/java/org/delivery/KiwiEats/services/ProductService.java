package org.delivery.KiwiEats.services;

import org.delivery.KiwiEats.entities.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Optional<Product> getProductById(Long id);
    List<Product> getAllProducts();
    Product createProduct(Product product);
    Optional<Product> updateProductById(Long id, Product product);
    Product deleteProductById(Long id);
}
 