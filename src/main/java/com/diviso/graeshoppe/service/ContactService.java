package com.diviso.graeshoppe.service;

import com.diviso.graeshoppe.domain.Contact;
import com.diviso.graeshoppe.service.dto.ContactDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Contact.
 */
public interface ContactService {

    /**
     * Save a contact.
     *
     * @param contactDTO the entity to save
     * @return the persisted entity
     */
    ContactDTO save(ContactDTO contactDTO);

    /**
     * Get all the contacts.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<ContactDTO> findAll(Pageable pageable);


    /**
     * Get the "id" contact.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ContactDTO> findOne(Long id);

    /**
     * Delete the "id" contact.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

	boolean createPublishMesssage(Contact contact, String status);
}
