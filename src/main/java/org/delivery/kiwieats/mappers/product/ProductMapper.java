package org.delivery.kiwieats.mappers.product;

import org.delivery.kiwieats.entities.product.Product;
import org.delivery.kiwieats.model.product.ProductDTO;
import org.mapstruct.Mapper;

@Mapper
public interface ProductMapper {
    Product productDtoToProduct(ProductDTO productDTO);

    ProductDTO productToProductDto(Product product);
}
