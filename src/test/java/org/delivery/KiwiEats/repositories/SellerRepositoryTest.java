package org.delivery.KiwiEats.repositories;

import jakarta.transaction.Transactional;
import org.delivery.KiwiEats.entities.Category;
import org.delivery.KiwiEats.entities.Product;
import org.delivery.KiwiEats.entities.Seller;
import org.delivery.KiwiEats.entities.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class SellerRepositoryTest {

    @Autowired
    SellerRepository sellerRepository;

    @Test
    @Transactional
    @Rollback
    public void testRegisterSeller() {
        Seller seller = sellerRepository.save(Seller.builder()
                .user(User.builder()
                        .username("FoodEater123")
                        .firstName("John")
                        .middleName("")
                        .lastName("Doe")
                        .email("foodlover@kiwieats.com")
                        .password("FoodIsGood")
                        .build())
                .productInStock(List.of(
                        Product.builder()
                                .productName("Corn")
                                .productImage("https://i.ibb.co/vxPqhf6/image.png")
                                .category(Category.VEGETABLE)
                                .build()
                ))
                .earnings(new BigDecimal(2000))
                .build());

        assertThat(seller).isNotNull();
        assertThat(seller.getId()).isNotNull();
    }
}
