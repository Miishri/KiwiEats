package org.delivery.KiwiEats.bootstrap;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.delivery.KiwiEats.entities.*;
import org.delivery.KiwiEats.entities.roles.Privilege;
import org.delivery.KiwiEats.entities.roles.Role;
import org.delivery.KiwiEats.entities.roles.User;
import org.delivery.KiwiEats.repositories.PrivilegeRepository;
import org.delivery.KiwiEats.repositories.RoleRepository;
import org.delivery.KiwiEats.repositories.SellerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BootstrapData implements CommandLineRunner {

  private final SellerRepository sellerRepository;
  private final RoleRepository roleRepository;
  private final PrivilegeRepository privilegeRepository;

  @Override
  public void run(String... args) {
    loadSellers();
  }

  private void loadSellers() {
    if (sellerRepository.count() == 0) {

      Privilege readPrivilege = createPrivilegeIfNotFound("READ_PRIVILEGE");
      Privilege writePrivilege = createPrivilegeIfNotFound("WRITE_PRIVILEGE");

      List<Privilege> adminPrivileges = Arrays.asList(readPrivilege, writePrivilege);

      createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
      createRoleIfNotFound("ROLE_SELLER", Collections.singletonList(readPrivilege));

      Role adminRole = roleRepository.findByName("ROLE_SELLER");

      User mangoUser =
          User.builder()
              .username("Aam Wala")
              .firstName("Mango")
              .middleName("")
              .lastName("Seller")
              .email("mango@lelo.com")
              .password("mangowala123")
              .enabled(true)
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

  @Transactional
  Privilege createPrivilegeIfNotFound(String name) {

    Privilege privilege = privilegeRepository.findByName(name);
    if (privilege == null) {
      privilege = Privilege.builder().name(name).build();
      privilegeRepository.save(privilege);
    }
    return privilege;
  }

  @Transactional
  Role createRoleIfNotFound(String name, Collection<Privilege> privileges) {

    Role role = roleRepository.findByName(name);
    if (role == null) {
      role = Role.builder().name(name).privileges(privileges).build();
      roleRepository.save(role);
    }

    return role;
  }
}
