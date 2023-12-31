package org.delivery.KiwiEats.bootstrap;


import lombok.RequiredArgsConstructor;
import org.delivery.KiwiEats.entities.Category;
import org.delivery.KiwiEats.entities.Product;
import org.delivery.KiwiEats.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BootstrapData implements CommandLineRunner {

    private final ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {
        loadProducts();
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
}
