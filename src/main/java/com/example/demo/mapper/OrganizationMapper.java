package com.example.demo.mapper;

import com.example.demo.client.OrganizationResponseGptDto;
import com.example.demo.dto.SearchResponseDto;
import com.example.demo.dto.SearchResponseType;
import com.example.demo.entity.Country;
import com.example.demo.entity.Organization;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrganizationMapper {

//    OrganizationMapper.IN

    public static OrganizationMapper INSTANCE = Mappers.getMapper(OrganizationMapper.class);

    @Mappings({
        @Mapping(source = "id", target = "orgId"),
            @Mapping(source = "type", target = "type", qualifiedByName = "stringToSearchType"),
        @Mapping(source = "name", target = "orgName"),
        @Mapping(source = "logoUrl", target = "orgLogoUrl"),
        @Mapping(source = "country.id", target = "countryId"),
        @Mapping(source = "country.name", target = "countryName"),
            @Mapping(source = "url", target = "orgUrl"),
            @Mapping(source = "favicon", target = "orgFavicon"),
            @Mapping(source = "address", target = "orgAddress")
    })
    SearchResponseDto organizationToSearchResponseDto(Organization organization);

    List<SearchResponseDto> organizationsToSearchResponseDtos(List<Organization> organizations);

    @Mappings({
            @Mapping(source = "country", target = "country", qualifiedByName = "contryToCountry")
    })
    Organization fromOrganizationGptDto(OrganizationResponseGptDto organizationGptDto);

    @Named("stringToSearchType")
    default SearchResponseType stringToSearchType(String type) {
        return SearchResponseType.ORGANIZATION;
    }

    @Named("contryToCountry")
    default Country contryToCountry(String country) {
        return null;
    }
}