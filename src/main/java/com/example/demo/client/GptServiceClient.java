package com.example.demo.client;

import com.example.demo.dto.ContactType;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GptServiceClient {

    @Value("${GPT_SERVICE_URL}")
    private String gptServiceUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public OrganizationResponseGptDto findByQuery(CompanyRequestGptDto companyRequestGptDto) {
//        HttpEntity<CompanyRequestGptDto> request = new HttpEntity<>(companyRequestGptDto);
//        return restTemplate.postForEntity(gptServiceUrl, request, OrganizationResponseGptDto.class).getBody();
        var contactDto = ContactResponseGptDto.builder()
                .type(ContactType.EMAIL)
                .description("sales department")
                .description("email@gmail.com")
                .build();
        return OrganizationResponseGptDto.builder()
                .contacts(Arrays.asList(contactDto))
                .url("http")
                .logoLink("http")
                .address("Address")
                .country("Serbia")
                .build();
    }
}
