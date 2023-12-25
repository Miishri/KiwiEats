package org.delivery.KiwiEats.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.delivery.KiwiEats.entities.Category;
import org.delivery.KiwiEats.entities.Product;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

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
  public void testGetProductById() {
    assertThat(getProductListFromRepo().get(0)).isNotNull();
  }

  @Test
  public void testGetAllProducts() {
    assertThat(getProductListFromRepo().size()).isEqualTo(1);
  }

  @Test
  @Transactional
  public void testCreateProduct() {
    assertThat(getProductListFromRepo().get(0).getProductName()).isEqualTo("Apple");
  }

  @Test
  @Transactional
  public void testDeleteProductById() {
    Long id = getProductListFromRepo().get(0).getId();
    productRepository.deleteById(id);
    assertThat(getProductListFromRepo()).isEmpty();
  }

  @AfterAll
  public static void tearDown(@Autowired ProductRepository setupRepository) {
    setupRepository.flush();
  }

  private List<Product> getProductListFromRepo() {
    return productRepository.findAll();
  }
}
