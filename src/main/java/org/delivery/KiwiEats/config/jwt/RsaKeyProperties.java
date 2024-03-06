package org.delivery.KiwiEats.config.jwt;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "rsa", ignoreInvalidFields = true)
public record RsaKeyProperties(RSAPublicKey publicKey, RSAPrivateKey privateKey) {}
