package com.example.demo.dto;

import com.example.demo.entity.Organization;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ContactCreationRqDto {

    private String type;

    private String description;

    private String value;

    private Long organizationId;
}
