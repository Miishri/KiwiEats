package org.delivery.KiwiEats.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.delivery.KiwiEats.exceptions.NotFoundException;
import org.delivery.KiwiEats.models.ProductDTO;
import org.delivery.KiwiEats.services.ProductService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
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
    public ResponseEntity<?> createProduct(@RequestBody ProductDTO productDTO) {
        ProductDTO createdProductDTO = productService.createProduct(productDTO);

        EntityModel<ProductDTO> entityModel = productModelAssembler.toModel(createdProductDTO);

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF)
                        .toUri()).body(entityModel);
    }

    @GetMapping(PRODUCT_PATH_ID)
    public EntityModel<ProductDTO> getProductById(@PathVariable("productId") Long productId) {
        log.debug("CONTROLLER - Get product by ID - Product ID: " + productId + " - CONTROLLER");

        ProductDTO productDTO = productService.getProductById(productId).orElseThrow(NotFoundException::new);

        return productModelAssembler.toModel(productDTO);
    }

    @GetMapping(PRODUCT_PATH)
    public CollectionModel<EntityModel<ProductDTO>> getAllProducts() {

        List<EntityModel<ProductDTO>> products = productService.getAllProducts().stream().map(productModelAssembler::toModel).toList();

        return CollectionModel.of(products, linkTo(methodOn(ProductController.class).getAllProducts()).withSelfRel());
    }

    @PutMapping(PRODUCT_PATH_ID)
    public ResponseEntity<?> updateProductById(@PathVariable("productId") Long productId, @RequestBody ProductDTO productDTO) {

        Optional<ProductDTO> updatedProduct = productService.updateProductById(productId, productDTO);

        if (updatedProduct.isEmpty()) throw new NotFoundException();

        EntityModel<ProductDTO> entityModel = productModelAssembler.toModel(updatedProduct.get());

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
