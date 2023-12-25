package org.delivery.KiwiEats.controllers;

import lombok.RequiredArgsConstructor;
import org.delivery.KiwiEats.entities.Product;
import org.delivery.KiwiEats.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class ProductController {
  public static final String PRODUCT_PATH = "/kiwi/product";
  public static final String PRODUCT_PATH_ID = PRODUCT_PATH + "/{productId}";

  private final ProductService productService;

  @PostMapping(PRODUCT_PATH)
  public ResponseEntity<Product> createProduct(@RequestBody Product product) {
    Product createdProduct = productService.createProduct(product);
    return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
  }

  @DeleteMapping(PRODUCT_PATH_ID)
  public ResponseEntity<Product> deleteProductById(@PathVariable("productId") Long productId) {
    return new ResponseEntity<>(productService.deleteProductById(productId), HttpStatus.ACCEPTED);
  }
}
