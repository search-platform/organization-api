//package com.example.demo.security;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jws;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import java.util.Date;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//@Service
//public class JwtService {
//
//    @Value("${auth.secret-key}")
//    private String secretKey;
//
//    @Value("${auth.token-expiration}")
//    private Integer tokenExpiration;
//
//    public String generateToken(String userName) {
//        long expirationTimeMillis = System.currentTimeMillis() + tokenExpiration;
//
//        return Jwts.builder()
//                .setSubject(userName)
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(expirationTimeMillis))
//                .signWith(SignatureAlgorithm.HS256, secretKey)
//                .compact();
//    }
//
//    public boolean isTokenValid(String token) {
//        try {
//            Jws<Claims> claimsJws = Jwts.parser()
//                    .setSigningKey(secretKey)
//                    .parseClaimsJws(token);
//
//            // Successfully parsed and verified the JWT
//            Claims claims = claimsJws.getBody();
//            String subject = claims.getSubject();
//            System.out.println("Subject: " + subject);
//        } catch (Exception e) {
//            // Handle exceptions, e.g., SignatureException, ExpiredJwtException, etc.
//            System.err.println("Failed to parse/verify JWT: " + e.getMessage());
//        }
//    }
//}
