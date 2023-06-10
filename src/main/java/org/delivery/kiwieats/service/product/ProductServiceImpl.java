package org.delivery.kiwieats.service.product;

import org.delivery.kiwieats.entities.product.Product;
import org.delivery.kiwieats.model.product.ProductDTO;

import java.util.List;
import java.util.Optional;

public class ProductServiceImpl implements ProductService {
    @Override
    public List<Product> listProducts() {
        return null;
    }

    @Override
    public Optional<Product> getProductById() {
        return Optional.empty();
    }

    @Override
    public Product saveProduct(Product product) {
        return null;
    }

    @Override
    public Optional<Product> updateProductById(Long productId, ProductDTO product) {
        return Optional.empty();
    }

    @Override
    public Boolean deleteProductById(Long productId) {
        return null;
    }
}
