package org.delivery.kiwieats.service.product;

import lombok.RequiredArgsConstructor;
import org.delivery.kiwieats.entities.Product;
import org.delivery.kiwieats.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public List<Product> listProducts() {
        return productRepository.findAll();
    }
    @Override
    public Optional<Product> getProductById(Long productId) {
        return Optional.of(productRepository.findById(productId))
                .orElse(null);
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Optional<Product> updateProductById(Long productId, Product product) {

        AtomicReference<Optional<Product>> atomicReference = new AtomicReference<>();

        productRepository.findById(productId).ifPresentOrElse(productFound -> {

            productFound.setProductName(product.getProductName());
            productFound.setProductDescription(product.getProductDescription());
            productFound.setProductImageLink(product.getProductImageLink());
            productFound.setProductType(product.getProductType());
            productFound.setProductStock(product.getProductStock());
            productFound.setSeller(product.getSeller());
            productFound.setOrder(product.getOrder());
            productFound.setCreatedDate(product.getCreatedDate());
            productFound.setPrice(product.getPrice());

            atomicReference.set(Optional.of(productRepository.save(productFound)));

        }, () -> {
            atomicReference.set(Optional.empty());
        });

        return Optional.empty();
    }

    @Override
    public Boolean deleteProductById(Long productId) {
        if (productRepository.existsById(productId)) {
            productRepository.deleteById(productId);
            return true;
        }
        return false;
    }
}
