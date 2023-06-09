package org.delivery.kiwieats.boostrap;

import lombok.RequiredArgsConstructor;
import org.delivery.kiwieats.entities.*;
import org.delivery.kiwieats.entities.customer.Customer;
import org.delivery.kiwieats.entities.order.Order;
import org.delivery.kiwieats.entities.product.Product;
import org.delivery.kiwieats.entities.product.ProductType;
import org.delivery.kiwieats.entities.rider.Rider;
import org.delivery.kiwieats.entities.seller.Seller;
import org.delivery.kiwieats.repositories.*;
import org.delivery.kiwieats.repositories.CustomerRepository;
import org.delivery.kiwieats.repositories.OrderRepository;
import org.delivery.kiwieats.repositories.ProductRepository;
import org.delivery.kiwieats.repositories.RiderRepository;
import org.delivery.kiwieats.repositories.SellerRepository;
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

    private Set<Order> orders;
    private Customer savedCustomer;
    @Transactional
    @Override
    public void run(String... args) throws Exception {
        loadSellerAndProduct();
        loadTestProducts();
        loadCustomer();
    }

    private void loadSellerAndProduct() {

        if (sellerRepository.count() == 0) {

            Seller seller = Seller.builder()
                    .totalCustomers(0)
                    .revenue(new BigDecimal("1100.90"))
                    .verified(true)
                    .build();


            sellerRepository.save(seller);
        }

        if (detailsRepository.count() < 3) {
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
            detailsRepository.save(userDetails);
        }
    }
    private void loadCustomer() {

        Customer customer = Customer.builder().build();

        UserDetails userDetails = UserDetails.builder()
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

        if (customerRepository.count() == 0) {
            loadTestOrders();
            customer.setOrders(orders);
            this.savedCustomer = customerRepository.save(customer);
            userDetails.setUserId(savedCustomer.getId());
        }

        if (detailsRepository.count() < 3) {
            detailsRepository.save(userDetails);
        }
    }
    private void loadTestOrders() {

        Order orders = Order.builder()
                .orderDetails("Products")
                .quantity(1)
                .customer(savedCustomer)
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
        riderRepository.save(rider);

        if (detailsRepository.count() < 3) {

            UserDetails userDetails = UserDetails.builder()
                    .userId(riderRepository.findAll().get(0).getId())
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


            detailsRepository.save(userDetails);
        }

        if (orderRepository.count() == 0) {
            this.orders = Set.of(orderRepository.save(orders));
        }
    }

    private void loadTestProducts() {

        if (productRepository.count() == 0) {

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
