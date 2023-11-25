package com.example.demo.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "organization_common_vector")
@Data
public class OrganizationCommonVector {

    @Id
    @Column(name = "organization_id")
    private Long organizationId;

    @Column(name = "search_vector")
    private String searchVector;  // Note: tsvector type is specific to PostgreSQL. In Java, you can use String.

    // Getters and Setters
}
