package org.delivery.KiwiEats.controllers;

import org.delivery.KiwiEats.entities.Category;
import org.delivery.KiwiEats.entities.Product;
import org.delivery.KiwiEats.repositories.ProductRepository;
import org.delivery.KiwiEats.services.ProductService;
import org.delivery.KiwiEats.services.ProductServiceImpl;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductService productService;

    @BeforeEach
    void beforeEach() {
        Product product = Product.builder()
                .productImage("Image")
                .productName("Test Product")
                .category(Category.VEGETABLE)
                .build();
        productRepository.save(product);
    }

    @Test
    void getProductById() throws Exception {
        Product product = productRepository.findAll().get(0);
        mockMvc.perform(get(ProductController.PRODUCT_PATH_ID, product.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.productName", is(product.getProductName())));
    }

    @Test
    void getProductByIdNotFound() throws Exception {
        mockMvc.perform(get(ProductController.PRODUCT_PATH_ID, 100L))
                .andExpect(status().isNotFound());
    }

    @Test
    void getAllProducts() throws Exception {
        mockMvc.perform(get(ProductController.PRODUCT_PATH)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()", is(1)));
    }

    @Test
    void updateProductById() {
    }

    @AfterEach
    void afterEach() {
        productRepository.findAll();
    }
}