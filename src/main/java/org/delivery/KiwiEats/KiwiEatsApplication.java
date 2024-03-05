package org.delivery.KiwiEats;

import org.delivery.KiwiEats.config.jwt.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication()
@EnableConfigurationProperties(RsaKeyProperties.class)
public class KiwiEatsApplication {
  public static void main(String... args) {
    SpringApplication.run(KiwiEatsApplication.class, args);
  }
}
