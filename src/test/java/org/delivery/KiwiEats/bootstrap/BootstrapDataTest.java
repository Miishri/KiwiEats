package org.delivery.KiwiEats.bootstrap;

import org.delivery.KiwiEats.repositories.PrivilegeRepository;
import org.delivery.KiwiEats.repositories.RoleRepository;
import org.delivery.KiwiEats.repositories.SellerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class BootstrapDataTest {

  @Autowired SellerRepository sellerRepository;
  @Autowired RoleRepository roleRepository;
  @Autowired BCryptPasswordEncoder bCryptPasswordEncoder;
  @Autowired PrivilegeRepository privilegeRepository;

  BootstrapData bootstrapData;

  @BeforeEach
  public void setup() {
    bootstrapData = new BootstrapData(sellerRepository, roleRepository,privilegeRepository, bCryptPasswordEncoder);
  }

  @Test
  void testRunMethod() throws Exception {
    bootstrapData.run();
    assertThat(sellerRepository.count()).isEqualTo(1);
  }
}
