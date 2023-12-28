package org.delivery.KiwiEats.controllers;

import org.delivery.KiwiEats.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(ProductController.class)
class ProductControllerTest {


    @MockBean
    ProductService productService;

    @Autowired
    MockMvc mockMvc;

    @Test
    void getProductById() {
    }

    @Test
    void getAllProducts() {
    }

    @Test
    void updateProductById() {
    }
}