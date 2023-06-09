package org.delivery.kiwieats.service.product;

import org.delivery.kiwieats.model.product.ProductDTO;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<ProductDTO> listProducts();
    Optional<ProductDTO> getProductById();

    ProductDTO saveProduct(ProductDTO productDTO);

    Optional<ProductDTO> updateProductById(Long productId, ProductDTO productDTO);

    Boolean deleteProductById(Long productId);

}
