package com.example.demo.client;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrganizationResponseGptDto {

    private String url;

    private String name;

    private String country;

    private String logoLink;

    private String faviconLink;

    private String address;

    private List<ContactResponseGptDto> contacts;

}
