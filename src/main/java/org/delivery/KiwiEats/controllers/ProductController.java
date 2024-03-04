package org.delivery.KiwiEats.controllers;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.delivery.KiwiEats.exception.NotFoundException;
import org.delivery.KiwiEats.models.ProductDTO;
import org.delivery.KiwiEats.services.product.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
public class ProductController {
  public static final String PRODUCT_PATH = "/kiwi/product";
  public static final String PRODUCT_PATH_ID = PRODUCT_PATH + "/{productId}";

  private final ProductService productService;

  @PostMapping(PRODUCT_PATH)
  public ProductDTO createProduct(@RequestBody ProductDTO productDTO) {
    return productService.createProduct(productDTO);
  }

  @GetMapping(PRODUCT_PATH_ID)
  public ProductDTO getProductById(@PathVariable("productId") Long productId) {
    log.debug("CONTROLLER - Get product by ID - Product ID: " + productId + " - CONTROLLER");
    return productService.getProductById(productId).orElseThrow(NotFoundException::new);
  }

  @GetMapping(PRODUCT_PATH)
  public List<ProductDTO> getAllProducts() {
    return productService.getAllProducts();
  }

  @PutMapping(PRODUCT_PATH_ID)
  public ProductDTO updateProductById(
      @PathVariable("productId") Long productId, @RequestBody ProductDTO productDTO) {

    Optional<ProductDTO> updatedProduct = productService.updateProductById(productId, productDTO);

    if (updatedProduct.isEmpty()) throw new NotFoundException();

    return updatedProduct.get();
  }

  @DeleteMapping(PRODUCT_PATH_ID)
  public ResponseEntity<?> deleteProductById(@PathVariable("productId") Long productId) {
    Boolean isDeleted = productService.deleteProductById(productId);
    if (!isDeleted) throw new NotFoundException();
    return ResponseEntity.noContent().build();
  }
}
