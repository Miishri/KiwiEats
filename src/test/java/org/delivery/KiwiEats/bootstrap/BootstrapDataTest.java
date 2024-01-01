package org.delivery.KiwiEats.bootstrap;

import org.delivery.KiwiEats.repositories.ProductRepository;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class BootstrapDataTest {

    @Autowired
    ProductRepository productRepository;


    BootstrapData bootstrapData;

    @BeforeEach
    public void setup() {
        bootstrapData = new BootstrapData(productRepository);
    }

    @Test
    void testRunMethod() throws Exception {
        bootstrapData.run();
        assertThat(productRepository.count()).isEqualTo(2);
    }
}
