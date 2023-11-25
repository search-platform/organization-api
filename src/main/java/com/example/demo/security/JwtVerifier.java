package com.example.demo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

public class JwtVerifier {

    private static final String SECRET_KEY = "yourSecretKeyBeSureThatItIsStrongSecretAndNoOneWillHackYou";



    public static void main(String[] args) {
        String jwtToVerify = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJleGFtcGxlLXN1YmplY3QiLCJpYXQiOjE3MDA4NjMwNjAsImV4cCI6MTcwMDg2NjY2MH0.UYWc4DnUe2sHN8jT-v18otPYnJx5OV7U-c0ckZDAEQQ"; // Replace with the JWT you want to verify

        try {
            Jws<Claims> claimsJws = Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(jwtToVerify);

            // Successfully parsed and verified the JWT
            Claims claims = claimsJws.getBody();
            String subject = claims.getSubject();
            System.out.println("Subject: " + subject);
        } catch (Exception e) {
            // Handle exceptions, e.g., SignatureException, ExpiredJwtException, etc.
            System.err.println("Failed to parse/verify JWT: " + e.getMessage());
        }
    }
}