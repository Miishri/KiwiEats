package org.delivery.KiwiEats.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import org.delivery.KiwiEats.entities.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest
public class ProductRepositoryTest {

  @Autowired ProductRepository productRepository;

  @Test
  @Transactional
  @Rollback
  public void testCreateProduct() {
    Product savedProduct =
        productRepository.save(
            Product.builder()
                .productName("Corn")
                .productImage("https://i.ibb.co/vxPqhf6/image.png")
                .category("VEGETABLE")
                .build());

    productRepository.flush();

    assertThat(savedProduct).isNotNull();
    assertThat(savedProduct.getId()).isNotNull();
  }
}
