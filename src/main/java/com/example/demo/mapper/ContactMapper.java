package com.example.demo.mapper;

import com.example.demo.dto.ContactCreationRqDto;
import com.example.demo.dto.ContactUpdationRqDto;
import com.example.demo.dto.SearchResponseDto;
import com.example.demo.dto.SearchResponseType;
import com.example.demo.entity.Contact;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ContactMapper {

    ContactMapper INSTANCE = Mappers.getMapper(ContactMapper.class);

    @Mappings({
        @Mapping(source = "type", target = "type", qualifiedByName = "stringToSearchResponseType"),
        @Mapping(source = "id", target = "contactId"),
        @Mapping(source = "value", target = "value"),
        @Mapping(source = "organization.id", target = "orgId"),
        @Mapping(source = "organization.name", target = "orgName"),
        @Mapping(source = "organization.logoUrl", target = "orgLogoUrl"),
        @Mapping(source = "organization.country.id", target = "countryId"),
        @Mapping(source = "organization.country.name", target = "countryName")
    })
    SearchResponseDto contactToSearchResponseDto(Contact contact);

    List<SearchResponseDto> contactsToSearchResponseDtos(List<Contact> contacts);

    Contact fromCreatingDto(ContactCreationRqDto contactCreationRqDto);

    Contact fromUpdatingDto(ContactUpdationRqDto contactUpdatingRqDto);

    @Named("stringToSearchResponseType")
    default SearchResponseType stringToSearchResponseType(String type) {
        return SearchResponseType.CONTACT;
    }
}
