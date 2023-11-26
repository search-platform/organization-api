package com.example.demo.service;

import com.example.demo.client.CompanyRequestGptDto;
import com.example.demo.client.GptServiceClient;
import com.example.demo.client.OrganizationResponseGptDto;
import com.example.demo.dto.SearchResponseDto;
import com.example.demo.dto.SearchResponseType;
import com.example.demo.entity.Contact;
import com.example.demo.entity.Country;
import com.example.demo.entity.Organization;
import com.example.demo.mapper.ContactMapper;
import com.example.demo.mapper.OrganizationMapper;
import com.example.demo.repository.ContactRepository;
import com.example.demo.repository.CountryRepository;
import com.example.demo.repository.OrganizationRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SearchService {

    private final JdbcTemplate jdbcTemplate;

    private final CountryRepository countryRepository;

    private final ContactRepository contactRepository;

    public static OrganizationMapper organizationMapper = Mappers.getMapper(OrganizationMapper.class);

    public static ContactMapper contactMapper = Mappers.getMapper(ContactMapper.class);

    private final OrganizationRepository organizationRepository;

    private final GptServiceClient gptServiceClient;

    public List<SearchResponseDto> findByOrganizationName(String query) {
        var entityResiltList = organizationRepository.findFuzzyByNameAndCountry("%"+query+"%");
        return organizationMapper.organizationsToSearchResponseDtos(entityResiltList);
    }



    public List<SearchResponseDto> findByPhoneValue(String query) {
        String sql = "SELECT con.id as contact_id, con.value as contact_value,\n" +
                "       c.id as country_id, c.name as country_name, o.id as org_id, o.name as org_name, o.\"logoUrl\" as org_logo_url\n" +
                " FROM contact con JOIN organization o on o.id = con.organization_id JOIN country c on c.id = o.county_id\n" +
                "WHERE con.type='PHONE' and con.value ILIKE ?";
        return jdbcTemplate.query(
                sql,
                new Object[]{"%"+query+"%"},
                (rs, rowNum) -> new SearchResponseDto(SearchResponseType.CONTACT,
                        rs.getLong("country_id"),
                        rs.getString("country_name"),
                        rs.getLong("org_id"),
                        rs.getString("org_name"),
                        rs.getString("org_logo_url"),
                        rs.getLong("contact_id"),
                        rs.getString("contact_value")
                )
        );
    }

    public List<SearchResponseDto> findByEmailValue(String query) {
        String sql = "SELECT con.id as contact_id, con.value as contact_value,\n" +
                "       c.id as country_id, c.name as country_name, o.id as org_id, o.name as org_name, o.\"logoUrl\" as org_logo_url\n" +
                " FROM contact con JOIN organization o on o.id = con.organization_id JOIN country c on c.id = o.county_id\n" +
                "WHERE con.type='EMAIL' and con.value ILIKE ?";
        return jdbcTemplate.query(
                sql,
                new Object[]{"%"+query+"%"},
                (rs, rowNum) -> new SearchResponseDto(SearchResponseType.CONTACT,
                        rs.getLong("country_id"),
                        rs.getString("country_name"),
                        rs.getLong("org_id"),
                        rs.getString("org_name"),
                        rs.getString("org_logo_url"),
                        rs.getLong("contact_id"),
                        rs.getString("contact_value")
                        )
        );
    }

    @Transactional
    public Organization findInGptAndSave(String country, String query) {
        OrganizationResponseGptDto companyResponseGptDto = gptServiceClient.findByQuery(CompanyRequestGptDto.builder().country(country).name(query).build());
        Country countryFromDb = countryRepository.findByName(companyResponseGptDto.getCountry());
        Organization organization = organizationMapper.fromOrganizationGptDto(companyResponseGptDto);
        organization.setName(companyResponseGptDto.getName());
        organization.setCountry(countryFromDb);
        organization.setLogoUrl(companyResponseGptDto.getUrl());

        List<Contact> contactList = contactMapper.fromGptDto(companyResponseGptDto.getContacts());

        var savedOrganization = organizationRepository.save(organization);

        for (Contact contact : contactList) {
            contact.setOrganization(savedOrganization);
        }

        contactRepository.saveAll(contactList);
        organization.setOrders(contactList);

//        var response = organizationMapper.organizationToSearchResponseDto(organization);
//        response.setCountryId(countryFromDb.getId());
//        response.setCountryName(countryFromDb.getName());
//        response.setOrgName(organization.getName());
//        response.setOrgLogoUrl(organization.getLogoUrl());
        return organizationRepository.findById(savedOrganization.getId()).get();
    }
}
