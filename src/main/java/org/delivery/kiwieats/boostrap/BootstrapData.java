package org.delivery.kiwieats.boostrap;

import lombok.RequiredArgsConstructor;
import org.delivery.kiwieats.entities.product.Product;
import org.delivery.kiwieats.entities.seller.Seller;
import org.delivery.kiwieats.entities.seller.SellerDetails;
import org.delivery.kiwieats.model.product.ProductType;
import org.delivery.kiwieats.repositories.product.ProductRepository;
import org.delivery.kiwieats.repositories.seller.SellerDetailsRepository;
import org.delivery.kiwieats.repositories.seller.SellerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class BootstrapData implements CommandLineRunner {

    private ProductRepository productRepository;
    private SellerRepository sellerRepository;
    private SellerDetailsRepository sellerDetailsRepository;

    public BootstrapData(ProductRepository productRepository, SellerRepository sellerRepository, SellerDetailsRepository sellerDetailsRepository) {
        this.productRepository = productRepository;
        this.sellerRepository = sellerRepository;
        this.sellerDetailsRepository = sellerDetailsRepository;
    }

    @Transactional
    @Override
    public void run(String... args) throws Exception {
        loadSellerAndProduct();
    }

    private void loadSellerAndProduct() {
        SellerDetails sellerDetails = SellerDetails.builder()
                .firstName("test-Rider-1")
                .lastName("delivery")
                .email("testRider@gmail.com")
                .password("random_string")
                .street("Riding Street")
                .careOf("proRider")
                .city("London")
                .postCode(13732)
                .country("UK")
                .build();

        SellerDetails savedDetails = sellerDetailsRepository.save(sellerDetails);

        Seller seller = Seller.builder()
                .totalCustomers(0)
                .sellerDetails(savedDetails)
                .revenue(new BigDecimal("1100.90"))
                .verified(true)
                .build();

        seller.setProducts(getTestProducts(seller));
        savedDetails.setSeller(seller);
        sellerDetailsRepository.save(savedDetails);
        sellerRepository.save(seller);
    }

    private Set<Product> getTestProducts(Seller seller) {
        final String PEXELS_URL = "https://www.pexels.com/photo/";
        Product cream = Product.builder()
                .productName("Cream")
                .productDescription("Cream to become white")
                .productImageLink(PEXELS_URL+"hands-woman-girl-morning-4046316/")
                .productType(ProductType.BEAUTY)
                .order(null)
                .seller(seller)
                .build();
        Product blender = Product.builder()
                .productName("Blender")
                .productDescription("Silky smooth shakes")
                .productImageLink(PEXELS_URL+"fruits-slices-on-a-blender-3094227/")
                .productType(ProductType.TECHNOLOGY)
                .order(null)
                .seller(seller)
                .build();
        Product keyboard = Product.builder()
                .productName("Mechanical Keyboard")
                .productDescription("Level up your gaming response")
                .productImageLink(PEXELS_URL+"close-up-photo-of-person-s-hand-on-mechanical-keyboard-7915219/")
                .productType(ProductType.GAMING)
                .order(null)
                .seller(seller)
                .build();
        Product dress = Product.builder()
                .productName("Red Dresses")
                .productDescription("Look the most prettiest!")
                .productImageLink(PEXELS_URL+"photo-of-women-s-clothing-1488463/")
                .productType(ProductType.CLOTHING)
                .order(null)
                .seller(seller)
                .build();
        Product biryani = Product.builder()
                .productName("Biryani")
                .productDescription("Take a spoonful of this yummy biryani")
                .productImageLink(PEXELS_URL+"biryani-rice-in-a-bowl-12737656/")
                .productType(ProductType.FOOD)
                .order(null)
                .seller(seller)
                .build();
        Product protein = Product.builder()
                .productName("Protein Powder")
                .productDescription("Lose fat, Gain muscles")
                .productImageLink(PEXELS_URL+"shallow-focus-of-chocolate-drink-926361/")
                .productType(ProductType.GYM)
                .order(null)
                .seller(seller)
                .build();
        Product gift = Product.builder()
                .productName("Marriage Present")
                .productDescription("Make them feel the happiest and luckiest")
                .productImageLink(PEXELS_URL+"several-gift-boxes-1666069/")
                .productType(ProductType.GIFT)
                .order(null)
                .seller(seller)
                .build();

        productRepository.save(cream);
        productRepository.save(blender);
        productRepository.save(keyboard);
        productRepository.save(dress);
        productRepository.save(biryani);
        productRepository.save(protein);
        productRepository.save(gift);

        return Set.of(cream, blender, keyboard, dress, biryani, protein, gift);
    }
}
