package org.delivery.kiwieats.service.product;

import org.delivery.kiwieats.model.product.ProductDTO;

import java.util.List;
import java.util.Optional;

public class ProductServiceImpl implements ProductService {
    @Override
    public List<ProductDTO> listProducts() {
        return null;
    }
    @Override
    public Optional<ProductDTO> getProductById() {
        return Optional.empty();
    }

    @Override
    public ProductDTO saveProduct(ProductDTO productDTO) {
        return null;
    }

    @Override
    public Optional<ProductDTO> updateProductById(Long productId, ProductDTO productDTO) {
        return Optional.empty();
    }

    @Override
    public Boolean deleteProductById(Long productId) {
        return null;
    }
}
