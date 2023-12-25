package org.delivery.KiwiEats.controllers;

import lombok.RequiredArgsConstructor;
import org.delivery.KiwiEats.services.ProductService;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ProductController {
    public static final String PRODUCT_PATH = "/kiwi/product";
    public static final String PRODUCT_PATH_ID = PRODUCT_PATH + "/{productId}";

    private final ProductService productService;
}
