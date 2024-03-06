package org.delivery.KiwiEats.mapper;

import org.delivery.KiwiEats.entities.Product;
import org.delivery.KiwiEats.models.ProductDTO;
import org.mapstruct.Mapper;

@Mapper
public interface ProductMapper {
  Product productDtoToProduct(ProductDTO productDTO);

  ProductDTO productToProductDto(Product product);
}
