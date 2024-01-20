package org.delivery.KiwiEats.bootstrap;


import lombok.RequiredArgsConstructor;
import org.delivery.KiwiEats.entities.Category;
import org.delivery.KiwiEats.entities.Product;
import org.delivery.KiwiEats.entities.Seller;
import org.delivery.KiwiEats.entities.User;
import org.delivery.KiwiEats.repositories.ProductRepository;
import org.delivery.KiwiEats.repositories.SellerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BootstrapData implements CommandLineRunner {

    private final ProductRepository productRepository;
    private final SellerRepository sellerRepository;

    @Override
    public void run(String... args) {
        loadProducts();
        loadSellers();
    }
    private void loadProducts() {
        if (productRepository.count() == 0) {
            Product mango = Product.builder()
                    .productName("Mango")
                    .productImage("https://i.ibb.co/Vt0mMq3/image.png")
                    .category(Category.FRUIT)
                    .build();

            Product sweetPotato = Product.builder()
                    .productName("Sweet Potato")
                    .productImage("https://i.ibb.co/mypKZ4P/image.png")
                    .category(Category.VEGETABLE)
                    .build();

            productRepository.save(mango);
            productRepository.save(sweetPotato);
        }
    }

    private void loadSellers() {
        if (sellerRepository.count() == 0) {
            Seller mangoSeller = Seller.builder()
                    .user(User.builder()
                            .username("Aam Wala")
                            .firstName("Mango")
                            .middleName("")
                            .lastName("Seller")
                            .email("mango@lelo.com")
                            .password("mangowala123")
                            .build())
                    .productInStock(List.of(
                            Product.builder()
                                    .productName("Mango")
                                    .productImage("https://i.ibb.co/Vt0mMq3/image.png")
                                    .category(Category.FRUIT)
                                    .build()
                    ))
                    .earnings(new BigDecimal(100))
                    .build();

            Seller sweetPotatoSeller = Seller.builder()
                    .user(User.builder()
                            .username("Shakar Kandi Wala")
                            .firstName("Sweet")
                            .middleName("Potato")
                            .lastName("Seller")
                            .email("sweetpotato@seller.com")
                            .password("sweetpotatowala123")
                            .build())
                    .productInStock(List.of(
                            Product.builder()
                                    .productName("Sweet Potato")
                                    .productImage("https://i.ibb.co/mypKZ4P/image.png")
                                    .category(Category.VEGETABLE)
                                    .build()
                    ))
                    .earnings(new BigDecimal(200))
                    .build();

            sellerRepository.save(mangoSeller);
            sellerRepository.save(sweetPotatoSeller);
        }
    }
}
