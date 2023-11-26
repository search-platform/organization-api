package com.example.demo.controller;

import com.example.demo.entity.Organization;
import com.example.demo.repository.OrganizationRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/organization/{organizationId}")
@RequiredArgsConstructor
public class OrganizationRestController {

    private final OrganizationRepository organizationRepository;

    // Create
//    @PostMapping
//    public Organization createOrganization(@RequestBody Organization organization) {
//        return organizationRepository.save(organization);
//    }

    @GetMapping
    public ResponseEntity<Organization> getOrganizationById(@PathVariable Long organizationId) {
        return organizationRepository.findById(organizationId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Update
//    @PutMapping
//    public ResponseEntity<Organization> updateOrganization(@PathVariable Long organizationId, @RequestBody Organization organizationDetails) {
//        return organizationRepository.findById(organizationId)
//                .map(organization -> {
//                    organization.setName(organizationDetails.getName());
//                    organization.setAddress(organizationDetails.getAddress());
//                    // Set other fields
//                    return ResponseEntity.ok(organizationRepository.save(organization));
//                })
//                .orElse(ResponseEntity.notFound().build());
//    }

    // Delete
//    @DeleteMapping
//    public ResponseEntity<?> deleteOrganization(@PathVariable Long organizationId) {
//        return organizationRepository.findById(organizationId)
//                .map(organization -> {
//                    organizationRepository.delete(organization);
//                    return ResponseEntity.ok().build();
//                })
//                .orElse(ResponseEntity.notFound().build());
//    }
}
