package com.example.demo.security.controller;

import com.example.demo.security.AuthRequest;
import com.example.demo.security.AuthResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping("/token")
    public ResponseEntity<AuthResponse> getToken(@RequestBody AuthRequest authRequest) {
        if (authRequest != null) {
            return ResponseEntity.ok(AuthResponse.builder().token("123").build());
        }
        return ResponseEntity.badRequest().build();
    }
}
