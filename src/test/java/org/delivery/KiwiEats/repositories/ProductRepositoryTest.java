package org.delivery.KiwiEats.repositories;

import org.delivery.KiwiEats.entities.Category;
import org.delivery.KiwiEats.entities.Product;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

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
}
