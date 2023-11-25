package com.example.demo.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;

public class JwtGenerator {

    private static final String SECRET_KEY = "yourSecretKeyBeSureThatItIsStrongSecretAndNoOneWillHackYou";

    public static void main(String[] args) {
        String subject = "example-subject";

        // Set the expiration time for the token (e.g., 1 hour)
        long expirationTimeMillis = System.currentTimeMillis() + 3600000;

        String jwt = Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(new Date(expirationTimeMillis))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();

        System.out.println("Generated JWT: " + jwt);
    }
}