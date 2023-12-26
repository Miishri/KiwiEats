package org.delivery.KiwiEats.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.delivery.KiwiEats.entities.Product;
import org.delivery.KiwiEats.exceptions.NotFoundException;
import org.delivery.KiwiEats.services.ProductService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Slf4j
@RequiredArgsConstructor
@RestController
public class ProductController {
    public static final String PRODUCT_PATH = "/kiwi/product";
    public static final String PRODUCT_PATH_ID = PRODUCT_PATH + "/{productId}";

    private final ProductService productService;
    private final ProductModelAssembler productModelAssembler;

    @PostMapping(PRODUCT_PATH)
    public ResponseEntity<?> createProduct(@RequestBody Product product) {
        Product createdProduct = productService.createProduct(product);

        EntityModel<Product> entityModel = productModelAssembler.toModel(createdProduct);

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF)
                        .toUri()).body(entityModel);
    }

    @GetMapping(PRODUCT_PATH_ID)
    public EntityModel<Product> getProductById(@PathVariable("productId") Long productId) {
        log.debug("CONTROLLER - Get product by ID - Product ID: " + productId + " - CONTROLLER");

        Product product = productService.getProductById(productId).orElseThrow(NotFoundException::new);

        return productModelAssembler.toModel(product);
    }

    @GetMapping(PRODUCT_PATH)
    public CollectionModel<EntityModel<Product>> getAllProducts() {

        List<EntityModel<Product>> products = productService.getAllProducts().stream().map(productModelAssembler::toModel).toList();

        return CollectionModel.of(products, linkTo(methodOn(ProductController.class).getAllProducts()).withSelfRel());
    }

    @PutMapping(PRODUCT_PATH_ID)
    public ResponseEntity<?> updateProductById(@PathVariable("productId") Long productId, @RequestBody Product product) {

        Optional<Product> updatedProduct = productService.updateProductById(productId, product);

        if (updatedProduct.isEmpty()) throw new NotFoundException();

        EntityModel<Product> entityModel = productModelAssembler.toModel(updatedProduct.get());

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF)
                        .toUri()).body(entityModel);
    }

    @DeleteMapping(PRODUCT_PATH_ID)
    public ResponseEntity<?> deleteProductById(@PathVariable("productId") Long productId) {
        Boolean isDeleted = productService.deleteProductById(productId);
        if(!isDeleted) throw new NotFoundException();
        return ResponseEntity.noContent().build();
    }
}
