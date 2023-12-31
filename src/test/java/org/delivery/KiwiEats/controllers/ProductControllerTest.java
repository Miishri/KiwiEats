package org.delivery.KiwiEats.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.delivery.KiwiEats.entities.Product;
import org.delivery.KiwiEats.repositories.ProductRepository;
import org.delivery.KiwiEats.services.ProductService;
import org.delivery.KiwiEats.services.ProductServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {

  @Autowired MockMvc mockMvc;

  @Autowired ProductRepository productRepository;

  @Autowired ProductService productService;

  @Autowired ProductServiceImpl productServiceImpl;

  @Autowired ObjectMapper objectMapper;

  @Test
  void getProductById() throws Exception {
    Product product = productServiceImpl.getAllProducts().get(0);
    mockMvc
        .perform(
            get(ProductController.PRODUCT_PATH_ID, product.getId())
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.productName", is(product.getProductName())));
  }

  @Test
  void getProductByIdNotFound() throws Exception {
    mockMvc.perform(get(ProductController.PRODUCT_PATH_ID, 100L)).andExpect(status().isNotFound());
  }

  @Test
  void getAllProducts() throws Exception {
    mockMvc
        .perform(get(ProductController.PRODUCT_PATH).accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$._embedded.productList.length()", is(1)));
  }

  @Test
  void updateProductById() throws Exception {
    Product product = productServiceImpl.getAllProducts().get(0);
    product.setProductName("Tested Product");

    mockMvc
        .perform(
            put(ProductController.PRODUCT_PATH_ID, product.getId())
                .content(objectMapper.writeValueAsString(product))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated());

    assertThat(product.getProductName())
        .isEqualTo(productService.getProductById(product.getId()).get().getProductName());
  }

  @Test
  void createProduct() throws Exception {
    Product product = Product.builder().productName("Test Product").build();
    mockMvc
        .perform(
            post(ProductController.PRODUCT_PATH)
                .content(objectMapper.writeValueAsString(product))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated());

    assertThat(product.getProductName())
        .isEqualTo(productService.getProductById(1L).get().getProductName());
  }

  @Test
  void deleteProductById() throws Exception {
    Product product = Product.builder().productName("Test Product").build();
    Long productId = productServiceImpl.createProduct(product).getId();
    mockMvc
        .perform(
            delete(ProductController.PRODUCT_PATH_ID, productId)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNoContent());
  }

  @Test
  void deleteProductNotFound() throws Exception {
    mockMvc
        .perform(delete(ProductController.PRODUCT_PATH_ID, 100L))
        .andExpect(status().isNotFound());
  }
}
