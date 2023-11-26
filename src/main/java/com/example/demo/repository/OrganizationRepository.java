package com.example.demo.repository;

import com.example.demo.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Long> {

    // Find organizations by type
    List<Organization> findByType(String type);

    // Find organizations by country
    List<Organization> findByCountryId(Long countryId);

    @Query(value = "SELECT o.id, o.type, o.name, o.address, o.county_id, o.logo_url as logo_url, o.fav_icon, o.url FROM organization o JOIN country c ON o.county_id = c.id WHERE o.name ILIKE :query", nativeQuery = true)
    List<Organization> findFuzzyByNameAndCountry(@Param("query") String query);

    // You can add more custom methods here as needed
}
