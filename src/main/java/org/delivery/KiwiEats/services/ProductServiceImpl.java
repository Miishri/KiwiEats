package org.delivery.KiwiEats.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.delivery.KiwiEats.entities.Product;
import org.delivery.KiwiEats.exceptions.NotFoundException;
import org.delivery.KiwiEats.repositories.ProductRepository;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;

  @Override
  public Optional<Product> getProductById(Long id) {
    log.debug("SERVICE - Get product by ID - Product ID: " + id + " - SERVICE");
    return productRepository.findById(id);
  }

  @Override
  public List<Product> getAllProducts() {
    log.debug("SERVICE - Get all products - SERVICE ");
    return productRepository.findAll();
  }

  @Override
  public Product createProduct(Product product) {
    log.debug("SERVICE--Create new product from controller--SERVICE");
    return productRepository.save(product);
  }

  @Override
  public Optional<Product> updateProductById(Long id, Product product) {
    log.debug("SERVICE - Update product by existing product and ID: " + id + " - SERVICE");
    AtomicReference<Optional<Product>> productReference = new AtomicReference<>();

    productRepository
        .findById(id)
        .ifPresentOrElse(
            productFound -> {
              productFound.setProductImage(product.getProductImage());
              productFound.setProductName(product.getProductName());
              productFound.setCategory(product.getCategory());
              productFound.setCreationDate(product.getCreationDate());

              productReference.set(Optional.of(productRepository.save(productFound)));
            },
            () -> {
              productReference.set(Optional.empty());
            });

    return productReference.get();
  }

  @Override
  public Boolean deleteProductById(Long id) {
    try {
      Product product = productRepository.findById(id).get();
      productRepository.deleteById(product.getId());
      log.debug("--SERVICE--Product with ID: " + id + " was deleted--");
    } catch (NoSuchElementException exception) {
      throw new NotFoundException();
    }
    return true;
  }
}
