package com.example.demo.repository;

import com.example.demo.entity.OrganizationCommonVector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationCommonVectorRepository extends JpaRepository<OrganizationCommonVector, Long> {

    // Custom methods for handling search vectors can be added here

    // Example: Find by organizationId
    OrganizationCommonVector findByOrganizationId(Long organizationId);

    // More complex queries, especially those utilizing tsvector features,
    // may require custom implementations using @Query annotation or native queries
}
