package com.example.demo.controller;

import com.example.demo.dto.ContactCreationRqDto;
import com.example.demo.dto.ContactUpdationRqDto;
import com.example.demo.entity.Contact;
import com.example.demo.mapper.ContactMapper;
import com.example.demo.repository.ContactRepository;
import com.example.demo.repository.OrganizationRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/organization/{organizationId}/contact")
@RequiredArgsConstructor
public class ContactController {

    public static ContactMapper contactMapper = Mappers.getMapper(ContactMapper.class);

    private final ContactRepository contactRepository;

    private final OrganizationRepository organizationRepository;

    // Create a new contact
    @PostMapping
    public Contact createContact(@PathVariable Long organizationId,
            @RequestBody ContactCreationRqDto contactDto) {
        var entity = contactMapper.fromCreatingDto(contactDto);
        var organization = organizationRepository.findById(organizationId);
        entity.setOrganization(organization.get());
        return contactRepository.save(entity);
    }

    // Retrieve all contacts
    @GetMapping
    public List<Contact> getAllContactsFromOrganization(@PathVariable Long organizationId) {
        return contactRepository.findByOrganizationId(organizationId);
    }

    // Get a single contact by ID
    @GetMapping("/{id}")
    public ResponseEntity<Contact> getContactById(@PathVariable Long id) {
        return contactRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Update a contact
    @PutMapping("/{id}")
    public ResponseEntity<Contact> updateContact(@PathVariable Long id, @PathVariable Long organizationId, @RequestBody ContactUpdationRqDto contactDto) {
        var organization = organizationRepository.findById(organizationId).get();
        var contactEntity = contactMapper.fromUpdatingDto(contactDto);
        contactEntity.setOrganization(organization);
        contactRepository.save(contactEntity);
        return ResponseEntity.ok(contactRepository.findById(id).get());
    }

    // Delete a contact
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteContact(@PathVariable Long id) {
        return contactRepository.findById(id)
                .map(contact -> {
                    contactRepository.delete(contact);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
