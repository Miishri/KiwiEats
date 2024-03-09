package org.delivery.KiwiEats.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.delivery.KiwiEats.entities.Product;
import org.delivery.KiwiEats.models.ProductDTO;
import org.delivery.KiwiEats.repositories.ProductRepository;
import org.delivery.KiwiEats.services.product.ProductService;
import org.delivery.KiwiEats.services.product.ProductServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser()
class ProductControllerTest {

  @Autowired MockMvc mockMvc;

  @Autowired ProductRepository productRepository;

  @Autowired ProductService productService;

  @Autowired ProductServiceImpl productServiceImpl;

  @Autowired ObjectMapper objectMapper;

  private final Long testIdNonExistent = 100L;

  @Test
  void getProductById() throws Exception {
    ProductDTO productDTO = productServiceImpl.getAllProducts().get(0);

    mockMvc
        .perform(
            get(ProductController.PRODUCT_PATH_ID, productDTO.getId())
                    .header("Authorization", getAuthorizedToken())
                    .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.productName", is(productDTO.getProductName())));
  }

  @Test
  void getProductByIdNotFound() throws Exception {
    mockMvc
        .perform(get(ProductController.PRODUCT_PATH_ID, testIdNonExistent)
                .header("Authorization", getAuthorizedToken()))
        .andExpect(status().isNotFound());
  }

  @Test
  void getAllProducts() throws Exception {
    mockMvc
        .perform(get(ProductController.PRODUCT_PATH)
                .header("Authorization", getAuthorizedToken())
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.length()", is(1)));
  }

  @Test
  void updateProductById() throws Exception {
    ProductDTO productDTO = getProduct();
    productDTO.setProductName("Tested Product");

    mockMvc
        .perform(
            put(ProductController.PRODUCT_PATH_ID, productDTO.getId())
                    .header("Authorization", getAuthorizedToken())
                .content(objectMapper.writeValueAsString(productDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());

    String expected = productDTO.getProductName();
    String result = productService.getProductById(productDTO.getId()).get().getProductName();

    assertThat(expected).isEqualTo(result);
  }

  @Test
  void createProduct() throws Exception {
    Product productDTO =
        Product.builder()
            .productName("Cherry")
            .productImage("https://i.ibb.co/p1y9sdk/image.png")
            .category("FRUIT")
            .build();

    mockMvc
        .perform(
            post(ProductController.PRODUCT_PATH)
                    .header("Authorization", getAuthorizedToken())
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(productDTO))
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

  @Test
  void deleteProductById() throws Exception {
    ProductDTO productDTO = ProductDTO.builder().productName("Test Product").build();
    Long productId = productServiceImpl.createProduct(productDTO).getId();
    mockMvc
        .perform(
            delete(ProductController.PRODUCT_PATH_ID, productId)
                    .header("Authorization", getAuthorizedToken())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNoContent());
  }

  @Test
  void deleteProductNotFound() throws Exception {
    mockMvc
        .perform(delete(ProductController.PRODUCT_PATH_ID, testIdNonExistent)
                .header("Authorization", getAuthorizedToken()))
        .andExpect(status().isNotFound());
  }

  private ProductDTO getProduct() {
    return productServiceImpl.getAllProducts().get(0);
  }

  private String getAuthorizedToken() throws Exception {
    MvcResult mvcResult = this.mockMvc.perform(post("/generate-token")
                    .with(httpBasic("Administrator", "admin")))
            .andExpect(status().isOk())
            .andReturn();

    return "Bearer " + mvcResult.getResponse().getContentAsString();
  }
}
