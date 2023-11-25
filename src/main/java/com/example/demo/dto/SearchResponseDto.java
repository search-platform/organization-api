package com.example.demo.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SearchResponseDto {

    private SearchType type;
    private Long countryId;
    private String countryName;
    private Long orgId;
    private String orgName;
    private String orgLogoUrl;
    private Long contactId;
    private String value;
}
