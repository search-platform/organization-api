package com.example.demo.security;

import com.example.demo.exception.AuthException;
import com.example.demo.security.dto.UserDto;
import com.example.demo.security.encoder.Sha256PasswordEncoder;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SecurityService {

//    @Value("${ACCESS_JWT_SECRET}")
    private String accessJwtSecret = "qweqowiepqoiwepo55499388383gwiepoqipwieqqweq08234iewr293wer";

    private final Sha256PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    public boolean isUserCredValid(String login, String rawPassword) {
        var storedPassword = userRepository.getEncodedPasswordByLogin(login);
        if (storedPassword == null) {
            return false;
        }
        var encryptedRequestPassword = passwordEncoder.encodePassword(rawPassword);
        return storedPassword.equals(encryptedRequestPassword);
    }

    public String issueToken(String login) {

        var storedPassword = userRepository.getEncodedPasswordByLogin(login);
        if (storedPassword == null) {
            throw new AuthException("UserRepository returned null");
        }
        var userPasswordToken = login + " " + storedPassword;

        long expirationTimeMillis = System.currentTimeMillis() + 3600000;

        return Jwts.builder()
                .setSubject(userPasswordToken)
                .setIssuedAt(new Date())
                .setExpiration(new Date(expirationTimeMillis))
                .signWith(SignatureAlgorithm.HS256, accessJwtSecret)
                .compact();
    }

    public boolean isTokenValid(String token) {

        try {
            Jws<Claims> claimsJws = Jwts.parser()
                    .setSigningKey(accessJwtSecret)
                    .parseClaimsJws(token);

            // Successfully parsed and verified the JWT
            Claims claims = claimsJws.getBody();
            String subject = claims.getSubject();
            String[] splitedSubject = subject.split(" ");
            String login = splitedSubject[0];
            String requestedPassword = splitedSubject[1];
            var storedPassword = userRepository.getEncodedPasswordByLogin(login);

            if (storedPassword == null) {
                return false;
            }

            return storedPassword.equals(requestedPassword);
        } catch (Exception e) {
            return false;
        }
    }
}
