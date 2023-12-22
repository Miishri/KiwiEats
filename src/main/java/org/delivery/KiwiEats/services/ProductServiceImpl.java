package org.delivery.KiwiEats.services;

import lombok.extern.slf4j.Slf4j;
import org.delivery.KiwiEats.entities.Product;
import org.delivery.KiwiEats.repositories.ProductRepository;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product createProduct(Product product) {
        log.debug("SERVICE--Create new product from controller--SERVICE");
        return productRepository.save(product);
    }

    @Override
    public Product deleteProductById(Long id) {
        if (!productRepository.existsById(id))
            throw new ServiceException("Product not found with ID: " + id);
        Product product = productRepository.findById(id).get();
        productRepository.deleteById(id);
        log.debug("--SERVICE--Product with ID: " + id + " was deleted--");
        return product;
    }
}
