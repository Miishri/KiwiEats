package org.delivery.KiwiEats.config;

import org.delivery.KiwiEats.services.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

  CustomUserDetailsService userDetailsService;
  public WebSecurityConfig(CustomUserDetailsService userDetailsService) {
    this.userDetailsService = userDetailsService;
  }

  @Bean
  public BCryptPasswordEncoder encoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests(
            (requests) ->
                requests
                    .requestMatchers("/login")
                    .permitAll()
                    .requestMatchers("/kiwi/seller/**", "kiwi/seller")
                    .hasAnyRole("SELLER")
                    .requestMatchers("/kiwi/customer/**", "kiwi/customer")
                    .hasAnyRole("CUSTOMER")
                    .requestMatchers("/kiwi/**")
                    .hasAnyRole("ADMIN")
                    .anyRequest()
                    .denyAll())
            .formLogin(Customizer.withDefaults())
            .rememberMe(Customizer.withDefaults())
            .userDetailsService(userDetailsService);

    return http.build();
  }
}
