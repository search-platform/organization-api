package com.example.demo.repository;

import com.example.demo.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
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
}
