package com.example.demo.security.controller;

import com.example.demo.security.SecurityService;
import com.example.demo.security.dto.AuthRequestDto;
import com.example.demo.security.dto.AuthResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final SecurityService securityService;

    @PostMapping("/token")
    public ResponseEntity<AuthResponseDto> getToken(@RequestBody AuthRequestDto authRequestDto) {
        if (!securityService.isUserCredValid(authRequestDto.getLogin(), authRequestDto.getPassword())) {
            return ResponseEntity.status(403).build();
        }
        var token = securityService.issueToken(authRequestDto.getLogin());
        return ResponseEntity.ok(AuthResponseDto.builder().token(token).build());
    }
}
