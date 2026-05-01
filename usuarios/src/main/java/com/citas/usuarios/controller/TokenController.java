package com.citas.usuarios.controller;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class TokenController {

    // crear clave para el hash
    private final String SECRET_KEY = "mi_clave_super_secreta_1234567890123456";

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public Integer extraerIdUsuario(String authorizationHeader) {
        // Validar que el header no sea nulo y tenga el formato correcto
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new RuntimeException("Token no válido o ausente");
        }

        String jwt = authorizationHeader.substring(7);

        Claims claims = Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(jwt)
                .getPayload();

        return Integer.parseInt(claims.getSubject());
    }
}