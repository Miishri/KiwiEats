package org.delivery.KiwiEats.config;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.delivery.KiwiEats.config.jwt.RsaKeyProperties;
import org.delivery.KiwiEats.config.user.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
  private final RsaKeyProperties rsaKeyProperties;
  CustomUserDetailsService userDetailsService;

  public WebSecurityConfig(RsaKeyProperties rsaKeyProperties, CustomUserDetailsService userDetailsService) {
    this.rsaKeyProperties = rsaKeyProperties;
    this.userDetailsService = userDetailsService;
  }

  @Bean
  public BCryptPasswordEncoder encoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  JwtEncoder jwtEncoder() {
    JWK jwk = new RSAKey
            .Builder(rsaKeyProperties.publicKey())
            .privateKey(rsaKeyProperties.privateKey())
            .build();
    JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
    return new NimbusJwtEncoder(jwks);
  }

  @Bean
  JwtDecoder jwtDecoder() {
    return NimbusJwtDecoder.withPublicKey(rsaKeyProperties.publicKey()).build();
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests(
            (requests) ->
                requests
                    .requestMatchers("/login", "/register", "/logout")
                    .permitAll()
                    .requestMatchers("/kiwi/seller/**", "kiwi/seller")
                    .hasAnyRole("SELLER")
                    .requestMatchers("/kiwi/customer/**", "kiwi/customer")
                    .hasAnyRole("CUSTOMER")
                    .requestMatchers("/kiwi/**")
                    .hasAnyRole("ADMIN")
                    .anyRequest()
                    .denyAll())
            .oauth2ResourceServer(oauth2 -> {
              oauth2.jwt(Customizer.withDefaults());
            })
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .formLogin(Customizer.withDefaults())
            .rememberMe(Customizer.withDefaults())
            .userDetailsService(userDetailsService);

    return http.build();
  }
}
