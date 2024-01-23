package org.delivery.KiwiEats.services;

import java.util.List;
import java.util.Optional;
import org.delivery.KiwiEats.models.ProductDTO;

public interface ProductService {
  Optional<ProductDTO> getProductById(Long id);

  List<ProductDTO> getAllProducts();

  ProductDTO createProduct(ProductDTO productDTO);

  Optional<ProductDTO> updateProductById(Long id, ProductDTO productDTO);

  Boolean deleteProductById(Long id);
}
