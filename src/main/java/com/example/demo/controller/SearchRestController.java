package com.example.demo.controller;

import com.example.demo.dto.SearchRequestDto;
import com.example.demo.dto.SearchResponseDto;
import com.example.demo.dto.SearchType;
import com.example.demo.repository.OrganizationRepository;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/search")
@RequiredArgsConstructor
public class SearchRestController {

    private final OrganizationRepository organizationRepository;

    @GetMapping
    public ResponseEntity<List<SearchResponseDto>> search(@RequestParam(name = "country") String country,
                                                          @RequestParam(name = "type") SearchType type,
                                                          @RequestBody SearchRequestDto searchRequest) {

        var emailDto = SearchResponseDto.builder()
                .type(SearchType.EMAIL)
                .orgId(1L)
                .orgName("Test Organization")
                .orgLogoUrl("http")
                .countryId(1L)
                .countryName("Italy")
                .value("test@gmail.com")
                .contactId(1L)
                .build();

        var phoneDto = SearchResponseDto.builder()
                .type(SearchType.PHONE)
                .orgId(1L)
                .orgName("Test Organization")
                .orgLogoUrl("http")
                .countryId(1L)
                .countryName("Italy")
                .value("+894865132156")
                .contactId(1L)
                .build();

        var result = organizationRepository.findFuzzyByName(searchRequest.getQuery());

        return ResponseEntity.ok(Arrays.asList(emailDto, phoneDto));
    }

    // Inner class to map the request body
}
