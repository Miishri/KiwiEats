package org.delivery.KiwiEats.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.delivery.KiwiEats.mapper.ProductMapper;
import org.delivery.KiwiEats.models.ProductDTO;
import org.delivery.KiwiEats.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;
  private final ProductMapper productMapper;

  @Override
  public Optional<ProductDTO> getProductById(Long id) {
    log.debug("SERVICE - Get product by ID - Product ID: " + id + " - SERVICE");
    return Optional.ofNullable(productMapper.productToProductDto(productRepository.findById(id).orElse(null)));
  }

  @Override
  public List<ProductDTO> getAllProducts() {
    log.debug("SERVICE - Get all products - SERVICE ");
    return productRepository.findAll().stream().map(productMapper::productToProductDto).toList();
  }

  @Override
  public ProductDTO createProduct(ProductDTO productDTO) {
    log.debug("SERVICE--Create new product from controller--SERVICE");

    return productMapper.productToProductDto(productRepository.save(productMapper.productDtoToProduct(productDTO)));
  }

  @Override
  public Optional<ProductDTO> updateProductById(Long id, ProductDTO productDTO) {
    log.debug("SERVICE - Update product by existing product and ID: " + id + " - SERVICE");
    AtomicReference<Optional<ProductDTO>> productReference = new AtomicReference<>();

    productRepository
        .findById(id)
        .ifPresentOrElse(
            productFound -> {
              productFound.setProductImage(productDTO.getProductImage());
              productFound.setProductName(productDTO.getProductName());
              productFound.setCategory(productDTO.getCategory());
              productFound.setCreationDate(productDTO.getCreationDate());

              productReference.set(Optional.of(productMapper.productToProductDto(productRepository.save(productFound))));
            },
            () -> {
              productReference.set(Optional.empty());
            });

    return productReference.get();
  }

  @Override
  public Boolean deleteProductById(Long id) {
    if (productRepository.existsById(id)) {
      productRepository.deleteById(id);
      log.debug("--SERVICE--Product with ID: " + id + " was deleted--");
      return true;
    }
    return false;
  }
}
