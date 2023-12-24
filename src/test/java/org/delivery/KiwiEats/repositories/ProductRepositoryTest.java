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
    assertThat(getProductFromRepo().getProductName()).isEqualTo("Apple");
  }

  @Test
  @Transactional
  public void testDeleteProductById() {
    Long id = getProductFromRepo().getId();
    productRepository.deleteById(id);
    assertThat(productRepository.findAll().size()).isEqualTo(0);
  }

  @AfterAll
  public static void tearDown(@Autowired ProductRepository setupRespository) {
    setupRespository.flush();
  }

  private Product getProductFromRepo() {
    return productRepository.findAll().get(0);
  }
}
