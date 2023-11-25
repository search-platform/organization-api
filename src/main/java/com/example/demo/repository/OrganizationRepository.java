package com.example.demo.repository;

import com.example.demo.entity.Country;
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

    @Query(value = "SELECT o.id, o.type, o.name, o.address, o.county_id, o.\"logoUrl\" as logo_url, SIMILARITY(o.name, :query) as rank FROM organization o WHERE SIMILARITY(o.name, :query) > 0 ORDER BY rank DESC;", nativeQuery = true)
    List<Organization> findFuzzyByName(@Param("query") String query);

    // You can add more custom methods here as needed
}
