package org.delivery.kiwieats.boostrap;

import org.delivery.kiwieats.repositories.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
class BootstrapDataTest {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private SellerRepository sellerRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private RiderRepository riderRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private UserDetailsRepository detailsRepository;

    BootstrapData bootstrapData;

    @BeforeEach
    void setUp() {
        bootstrapData = new BootstrapData(productRepository,
                sellerRepository,
                orderRepository,
                riderRepository,
                customerRepository,
                detailsRepository);
    }

    @Test
    @Transactional
    void testBootstrapDataRun() throws Exception {
        bootstrapData.run(null);

        assertThat(productRepository.count()).isEqualTo(7);
        assertThat(sellerRepository.count()).isEqualTo(1);
        assertThat(orderRepository.count()).isEqualTo(1);
        assertThat(riderRepository.count()).isEqualTo(1);
        assertThat(customerRepository.count()).isEqualTo(1);
        assertThat(detailsRepository.count()).isEqualTo(3);

    }
}