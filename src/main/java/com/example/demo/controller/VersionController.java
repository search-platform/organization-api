package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/health")
public class VersionController {

    private static final String VERSION = "0.4";

    @GetMapping
    public ResponseEntity<String> getVersion() {
        return ResponseEntity.ok(VERSION);
    }
}
