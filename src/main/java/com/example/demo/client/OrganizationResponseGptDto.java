package com.example.demo.client;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrganizationResponseGptDto {

    private List<ContactResponseGptDto> contacts;

    private String url;

    private String country;

    private String logoLink;

    private String address;

    private String name;
}
