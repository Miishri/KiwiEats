package org.delivery.KiwiEats.bootstrap;

import org.delivery.KiwiEats.repositories.ProductRepository;
import org.delivery.KiwiEats.repositories.SellerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class BootstrapDataTest {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    SellerRepository sellerRepository;

    BootstrapData bootstrapData;

    @BeforeEach
    public void setup() {
        bootstrapData = new BootstrapData(productRepository, sellerRepository);
    }

    @Test
    void testRunMethod() throws Exception {
        bootstrapData.run();
        assertThat(productRepository.count()).isEqualTo(2);
    }
}
