package com.example.demo.client;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CompanyRequestGptDto {

    private String country;

    private String query;
}
