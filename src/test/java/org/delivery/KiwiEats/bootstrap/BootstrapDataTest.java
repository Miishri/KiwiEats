package org.delivery.KiwiEats.bootstrap;

import org.delivery.KiwiEats.repositories.PrivilegeRepository;
import org.delivery.KiwiEats.repositories.RoleRepository;
import org.delivery.KiwiEats.repositories.SellerRepository;
import org.delivery.KiwiEats.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class BootstrapDataTest {

  @Autowired SellerRepository sellerRepository;
  @Autowired RoleRepository roleRepository;
  @Autowired BCryptPasswordEncoder bCryptPasswordEncoder;
  @Autowired PrivilegeRepository privilegeRepository;
  @Autowired UserRepository userRepository ;

  BootstrapData bootstrapData;

  @BeforeEach
  public void setup() {
    bootstrapData = new BootstrapData(sellerRepository, roleRepository,privilegeRepository, bCryptPasswordEncoder, userRepository);
  }

  @Test
  void testRunMethod() throws Exception {
    bootstrapData.run();
    assertThat(userRepository.count()).isEqualTo(2);
  }
}