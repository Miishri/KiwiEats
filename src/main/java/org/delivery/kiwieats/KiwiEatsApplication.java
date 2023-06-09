package org.delivery.kiwieats;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class KiwiEatsApplication {
	public static void main(String[] args) {
		SpringApplication.run(KiwiEatsApplication.class, args);
	}
}
