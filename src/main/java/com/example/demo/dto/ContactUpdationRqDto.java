package com.example.demo.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ContactUpdationRqDto {

    private Long id;

    private String type;

    private String description;

    private String value;
}
