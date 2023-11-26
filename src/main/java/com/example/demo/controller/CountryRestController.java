package com.example.demo.controller;

import com.example.demo.entity.Country;
import com.example.demo.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/countries")
@RequiredArgsConstructor
public class CountryRestController {

    private final CountryRepository countryRepository;

    @GetMapping
    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    @GetMapping("/{countryId}")
    public ResponseEntity<Country> getCountryById(@PathVariable Long countryId) {
        return countryRepository.findById(countryId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
