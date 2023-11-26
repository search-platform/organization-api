package com.example.demo.client;

import com.example.demo.dto.ContactType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ContactResponseGptDto {

    private ContactType type;

    private String description;

    private String value;
}
