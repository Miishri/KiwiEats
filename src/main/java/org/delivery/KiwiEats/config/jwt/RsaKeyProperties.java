package org.delivery.KiwiEats.config.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@ConfigurationProperties(prefix = "rsa", ignoreInvalidFields = true)
public record RsaKeyProperties(RSAPublicKey publicKey, RSAPrivateKey privateKey) {
}
