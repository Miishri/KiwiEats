package org.delivery.KiwiEats.bootstrap;

import lombok.RequiredArgsConstructor;
import org.delivery.KiwiEats.entities.Product;
import org.delivery.KiwiEats.entities.Seller;
import org.delivery.KiwiEats.entities.roles.Role;
import org.delivery.KiwiEats.entities.roles.User;
import org.delivery.KiwiEats.repositories.RoleRepository;
import org.delivery.KiwiEats.repositories.SellerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BootstrapData implements CommandLineRunner {

  private final SellerRepository sellerRepository;
  private final RoleRepository roleRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;
  private boolean loaded = false;

  @Override
  public void run(String... args) {
    createPrivileges();
    loadSellers();
  }

  private void createPrivileges() {
    if (!loaded) {
      Role adminRole = Role.builder().name("ADMIN").build();

      Role customerRole = Role.builder().name("CUSTOMER").build();

      Role sellerRole = Role.builder().name("SELLER").build();

      roleRepository.save(adminRole);
      roleRepository.save(customerRole);
      roleRepository.save(sellerRole);

      this.loaded = true;
    }
  }

  private void loadSellers() {
    if (sellerRepository.count() == 0) {
      Role adminRole = roleRepository.findByName("SELLER");

      User mangoUser =
          User.builder()
              .username("Aam Wala")
              .firstName("Mango")
              .middleName("")
              .lastName("Seller")
              .email("mango@lelo.com")
              .password(bCryptPasswordEncoder.encode("mangowala123"))
              .tokenExpired(false)
              .roles(Collections.singleton(adminRole))
              .build();

      Seller mangoSeller = Seller.builder().earnings(new BigDecimal(100)).build();

      mangoUser.setSeller(mangoSeller);
      mangoSeller.setUser(mangoUser);

      List<Product> mangoProducts =
          List.of(
              Product.builder()
                  .productName("Mango")
                  .productImage("https://i.ibb.co/Vt0mMq3/image.png")
                  .category("FRUIT")
                  .price(new BigDecimal("100"))
                  .build());

      mangoProducts.forEach(product -> product.setSeller(mangoSeller));

      mangoSeller.setProductInStock(mangoProducts);

      sellerRepository.save(mangoSeller);
    }
  }
}
