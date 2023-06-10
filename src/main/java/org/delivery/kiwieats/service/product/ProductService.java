package org.delivery.kiwieats.service.product;

import org.delivery.kiwieats.entities.product.Product;
import org.delivery.kiwieats.model.product.ProductDTO;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> listProducts();
    Optional<Product> getProductById();

    Product saveProduct(Product product);

    Optional<Product> updateProductById(Long productId, ProductDTO product);

    Boolean deleteProductById(Long productId);

}
