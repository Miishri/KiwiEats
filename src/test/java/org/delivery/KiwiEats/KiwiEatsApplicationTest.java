package org.delivery.KiwiEats;

import org.delivery.KiwiEats.config.jwt.RsaKeyProperties;
import org.junit.jupiter.api.Test;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@EnableConfigurationProperties(RsaKeyProperties.class)
class KiwiEatsApplicationTest {
  @Test
  void contextLoads() {}
}
