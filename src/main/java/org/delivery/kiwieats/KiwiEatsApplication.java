package org.delivery.kiwieats;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@ComponentScan
public class KiwiEatsApplication {
	public static void main(String[] args) {
		SpringApplication.run(KiwiEatsApplication.class, args);
	}
}
