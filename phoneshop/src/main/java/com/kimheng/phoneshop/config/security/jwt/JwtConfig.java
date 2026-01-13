package com.kimheng.phoneshop.config.security.jwt;

import java.security.Key;

import org.springframework.context.annotation.Configuration;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Configuration
public class JwtConfig {
    private final String secret = "Abkdjfklasfadfewwicvasidfjasdlhfkasdhfiolqweye";

    public Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
