package org.delivery.KiwiEats.mapper;


import org.delivery.KiwiEats.entities.Product;
import org.mapstruct.Mapper;

@Mapper
public interface ProductMapper {
    Product productDtoToProduct(org.delivery.KiwiEats.models.ProductDTO productDTO);
    org.delivery.KiwiEats.models.ProductDTO productToProductDto(Product product);
}
