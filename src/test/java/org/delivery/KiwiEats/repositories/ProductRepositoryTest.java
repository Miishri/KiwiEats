package org.delivery.KiwiEats.repositories;

import org.delivery.KiwiEats.entities.Category;
import org.delivery.KiwiEats.entities.Product;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class ProductRepositoryTest {

  @Autowired ProductRepository productRepository;

  @BeforeAll
  public static void setUp(@Autowired ProductRepository setupRepository) {
    Product product =
        Product.builder()
            .productImage("Image")
            .productName("Apple")
            .category(Category.FRUIT)
            .build();
    setupRepository.save(product);
  }

  @Test
  @Transactional
  public void testCreateProduct() {
    Product product = Product.builder().productName("Apple").build();
    assertThat(productRepository.findAll().get(0).getProductName())
        .isEqualTo(product.getProductName());
  }

  @Test
  @Transactional
  public void testDeleteProductById() {
    assertThat(productRepository.findAll().get(0).getProductName()).isEqualTo("Apple");
    Long id = productRepository.findAll().get(0).getId();
    productRepository.deleteById(id);
    assertThat(productRepository.findAll().size()).isEqualTo(0);
  }

  @AfterAll
  public static void tearDown(@Autowired ProductRepository setupRespository) {
    setupRespository.flush();
  }
}
