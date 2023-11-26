package com.example.demo.controller;

import com.example.demo.dto.SearchRequestDto;
import com.example.demo.dto.SearchResponseDto;
import com.example.demo.dto.SearchResponseType;
import com.example.demo.dto.SearchType;
import com.example.demo.mapper.OrganizationMapper;
import com.example.demo.repository.OrganizationRepository;
import com.example.demo.service.SearchService;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
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

    private final SearchService searchService;

    @GetMapping
    public ResponseEntity<List<SearchResponseDto>> search(@RequestParam(name = "type") SearchType type,
                                                          @RequestParam(name = "query") String query) {

        if (type == SearchType.ALL) {
            throw new RuntimeException("Not implemented FTS");
        } else if (type == SearchType.ORGANIZATION) {
            return ResponseEntity.ok(searchService.findByOrganizationName(query));
        } else if (type == SearchType.EMAIL) {
            return ResponseEntity.ok(searchService.findByEmailValue(query));
        } else if (type == SearchType.PHONE) {
            return ResponseEntity.ok(searchService.findByPhoneValue(query));
        }

        return ResponseEntity.ok(null);
    }

    // Inner class to map the request body
}
