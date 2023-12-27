package org.delivery.KiwiEats.services;

import java.util.List;
import java.util.Optional;
import org.delivery.KiwiEats.entities.Product;

public interface ProductService {
  Optional<Product> getProductById(Long id);

  List<Product> getAllProducts();

  Product createProduct(Product product);

  Optional<Product> updateProductById(Long id, Product product);

  Boolean deleteProductById(Long id);
}
