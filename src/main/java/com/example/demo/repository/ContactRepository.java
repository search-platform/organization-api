package com.example.demo.repository;

import com.example.demo.dto.SearchResponseDto;
import com.example.demo.entity.Contact;
import com.example.demo.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

    // Find contacts by type
    List<Contact> findByType(String type);

    // Find contacts associated with a specific organization
    List<Contact> findByOrganizationId(Long organizationId);

    // Find contacts by description
    List<Contact> findByDescriptionContaining(String description);

    // Additional custom methods can be added here as needed

//    @Query(value = "SELECT con.id, con.description, con.value, con.type,\n" +
//            "       c.name, o.id, o.type, o.name, o.address, o.county_id, o.\"logoUrl\" as logo_url,\n" +
//            "       SIMILARITY(con.value, :query) as rank\n" +
//            "FROM contact con JOIN organization o on o.id = con.organization_id JOIN country c on c.id = o.county_id\n" +
//            "WHERE c.name='Serbia' and con.type='EMAIL' and SIMILARITY(con.value, :query) > 0 ORDER BY rank DESC;", nativeQuery = true)
//    List<Contact> findFuzzyByValueAndCountry(@Param("query") String query, @Param("country") String country);

    @Query(value = "SELECT con.id FROM contact con JOIN organization o on o.id = con.organization_id JOIN country c on c.id = o.county_id WHERE c.name=:country and SIMILARITY(con.value, :query) > 0", nativeQuery = true)
    List<Long> findFuzzyIdsByValue(@Param("query") String query, @Param("country") String country);

//    @Query(value = "SELECT new com.example.demo.dto.SearchResponseDto('EMAIL', ) FROM Contact con JOIN Organization org JOIN Country country")
//    List<SearchResponseDto> findResponseDtoByContactId();
}
