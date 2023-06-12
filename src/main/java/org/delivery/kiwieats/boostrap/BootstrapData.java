package org.delivery.kiwieats.boostrap;

import lombok.RequiredArgsConstructor;
import org.delivery.kiwieats.entities.*;
import org.delivery.kiwieats.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class BootstrapData implements CommandLineRunner {
    private final String PEXELS_URL = "https://www.pexels.com/photo/";

    private final ProductRepository productRepository;
    private final SellerRepository sellerRepository;
    private final OrderRepository orderRepository;
    private final RiderRepository riderRepository;
    private final CustomerRepository customerRepository;
    private final UserDetailsRepository detailsRepository;

    @Transactional
    @Override
    public void run(String... args) throws Exception {
        loadSellerAndProduct();
        loadTestProducts();
        loadCustomer();
        getTestOrders();
    }

    private void loadSellerAndProduct() {

        Seller seller = Seller.builder()
                .totalCustomers(0)
                .revenue(new BigDecimal("1100.90"))
                .verified(true)
                .build();

        if (sellerRepository.count() == 0) {
            sellerRepository.save(seller);
        }

        UserDetails userDetails = UserDetails.builder()
                .userId(sellerRepository.findAll().get(0).getId())
                .firstName("test")
                .lastName("seller")
                .email("testSeller@gmail.com")
                .password("random_string")
                .street("seller street")
                .careOf("proSeller")
                .city("London")
                .postCode(14143)
                .phone(151412315)
                .verified(true)
                .country("UK")
                .build();

        if (detailsRepository.count() == 0) {
            detailsRepository.save(userDetails);
        }
    }
    private void loadCustomer() {

        Customer customer = Customer.builder().build();

        UserDetails userDetails = UserDetails.builder()
                .userId(customerRepository.save(customer).getId())
                .firstName("test")
                .lastName("customer")
                .email("testCustomer@gmail.com")
                .password("random_string")
                .street("customer street")
                .careOf("customer")
                .city("Berlin")
                .postCode(15512)
                .phone(1314151241)
                .verified(false)
                .country("Germany")
                .build();

        customer.setOrders(getTestOrders());
        if (customerRepository.count() == 0) {
            customerRepository.save(customer);
        }

        if (detailsRepository.count() == 1) {
            detailsRepository.save(userDetails);
        }
    }
    private Set<Order> getTestOrders() {
        Order orders = Order.builder()
                .orderDetails("Products")
                .quantity(1)
                .customer(customerRepository.findAll().get(0))
                .active(true)
                .products(new HashSet<>(productRepository.findAll()))
                .build();

        Rider rider = Rider.builder()
                .orders(Set.of(orders))
                .tips(new BigDecimal("11.45"))
                .totalPaid(new BigDecimal("140.20"))
                .verified(true)
                .build();

        rider.setOrders(Set.of(orders));

        UserDetails userDetails = UserDetails.builder()
                .userId(riderRepository.save(rider).getId())
                .firstName("test")
                .lastName("rider")
                .email("testRider@gmail.com")
                .password("not_random")
                .street("rider street")
                .careOf("proRider")
                .city("Stockholm")
                .postCode(12314)
                .phone(124074068)
                .verified(false)
                .country("SE")
                .build();
        orders.setRider(riderRepository.findAll().get(0));

        if (detailsRepository.count() == 2) {
            detailsRepository.save(userDetails);
        }
        if (orderRepository.count() == 0) {
            orderRepository.save(orders);
        }
        return orderRepository.findAll().get(0).getCustomer().getOrders();
    }
    private void loadTestProducts() {

        Product cream = Product.builder()
                .productName("Cream")
                .productDescription("Cream to become white")
                .productImageLink(PEXELS_URL+"hands-woman-girl-morning-4046316/")
                .productType(ProductType.BEAUTY)
                .price(new BigDecimal("11.99"))
                .productStock(15)
                .order(null)
                .build();
        Product blender = Product.builder()
                .productName("Blender")
                .productDescription("Silky smooth shakes")
                .productImageLink(PEXELS_URL+"fruits-slices-on-a-blender-3094227/")
                .productType(ProductType.TECHNOLOGY)
                .price(new BigDecimal("26.10"))
                .productStock(20)
                .order(null)
                .build();
        Product keyboard = Product.builder()
                .productName("Mechanical Keyboard")
                .productDescription("Level up your gaming response")
                .productImageLink(PEXELS_URL+"close-up-photo-of-person-s-hand-on-mechanical-keyboard-7915219/")
                .productType(ProductType.GAMING)
                .price(new BigDecimal("20"))
                .productStock(5)
                .order(null)
                .build();
        Product dress = Product.builder()
                .productName("Red Dresses")
                .productDescription("Look the most prettiest!")
                .productImageLink(PEXELS_URL+"photo-of-women-s-clothing-1488463/")
                .productType(ProductType.CLOTHING)
                .price(new BigDecimal("30.99"))
                .productStock(29)
                .order(null)
                .build();
        Product biryani = Product.builder()
                .productName("Biryani")
                .productDescription("Take a spoonful of this yummy biryani")
                .productImageLink(PEXELS_URL+"biryani-rice-in-a-bowl-12737656/")
                .productType(ProductType.FOOD)
                .price(new BigDecimal("6"))
                .productStock(60)
                .order(null)
                .build();
        Product protein = Product.builder()
                .productName("Protein Powder")
                .productDescription("Lose fat, Gain muscles")
                .productImageLink(PEXELS_URL+"shallow-focus-of-chocolate-drink-926361/")
                .productType(ProductType.GYM)
                .price(new BigDecimal("18.99"))
                .productStock(120)
                .order(null)
                .build();
        Product gift = Product.builder()
                .productName("Marriage Present")
                .productDescription("Make them feel the happiest and luckiest")
                .productImageLink(PEXELS_URL+"several-gift-boxes-1666069/")
                .productType(ProductType.GIFT)
                .price(new BigDecimal("50"))
                .productStock(9)
                .order(null)
                .build();

        if (productRepository.count() == 0) {
            cream.setSeller(sellerRepository.findAll().get(0));
            blender.setSeller(sellerRepository.findAll().get(0));
            keyboard.setSeller(sellerRepository.findAll().get(0));
            dress.setSeller(sellerRepository.findAll().get(0));
            biryani.setSeller(sellerRepository.findAll().get(0));
            protein.setSeller(sellerRepository.findAll().get(0));
            gift.setSeller(sellerRepository.findAll().get(0));
            productRepository.save(cream);
            productRepository.save(blender);
            productRepository.save(keyboard);
            productRepository.save(dress);
            productRepository.save(biryani);
            productRepository.save(protein);
            productRepository.save(gift);
        }

    }
    private Product getBeautyProduct() {
        return Product.builder()
                .productName("Cream")
                .productDescription("Cream to become white")
                .productImageLink(PEXELS_URL+"hands-woman-girl-morning-4046316/")
                .productType(ProductType.BEAUTY)
                .order(null)
                .seller(sellerRepository.findAll().get(0))
                .build();
    }
    private Product getTechProduct() {
        return Product.builder()
                .productName("Blender")
                .productDescription("Silky smooth shakes")
                .productImageLink(PEXELS_URL+"fruits-slices-on-a-blender-3094227/")
                .productType(ProductType.TECHNOLOGY)
                .order(null)
                .seller(sellerRepository.findAll().get(0))
                .build();
    }
    private Product getGamingProduct() {
        return Product.builder()
                .productName("Mechanical Keyboard")
                .productDescription("Level up your gaming response")
                .productImageLink(PEXELS_URL+"close-up-photo-of-person-s-hand-on-mechanical-keyboard-7915219/")
                .productType(ProductType.GAMING)
                .order(null)
                .seller(sellerRepository.findAll().get(0))
                .build();
    }
    private Product getClothingProduct() {
        return Product.builder()
                .productName("Red Dresses")
                .productDescription("Look the most prettiest!")
                .productImageLink(PEXELS_URL+"photo-of-women-s-clothing-1488463/")
                .productType(ProductType.CLOTHING)
                .order(null)
                .seller(sellerRepository.findAll().get(0))
                .build();
    }
    private Product getFoodProduct() {
        return Product.builder()
                .productName("Biryani")
                .productDescription("Take a spoonful of this yummy biryani")
                .productImageLink(PEXELS_URL+"biryani-rice-in-a-bowl-12737656/")
                .productType(ProductType.FOOD)
                .order(null)
                .seller(sellerRepository.findAll().get(0))
                .build();
    }
    private Product getGymProduct() {
        return Product.builder()
                .productName("Protein Powder")
                .productDescription("Lose fat, Gain muscles")
                .productImageLink(PEXELS_URL+"shallow-focus-of-chocolate-drink-926361/")
                .productType(ProductType.GYM)
                .order(null)
                .seller(sellerRepository.findAll().get(0))
                .build();
    }
    private Product getGiftProduct() {
        return Product.builder()
                .productName("Marriage Present")
                .productDescription("Make them feel the happiest and luckiest")
                .productImageLink(PEXELS_URL+"several-gift-boxes-1666069/")
                .productType(ProductType.GIFT)
                .order(null)
                .seller(sellerRepository.findAll().get(0))
                .build();
    }
}
