package org.delivery.kiwieats.service.product;

import org.delivery.kiwieats.entities.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> listProducts();
    Optional<Product> getProductById(Long productId);

    Product saveProduct(Product product);

    Optional<Product> updateProductById(Long productId, Product product);

    Boolean deleteProductById(Long productId);

}
