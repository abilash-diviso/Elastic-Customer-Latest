package com.diviso.graeshoppe.service.mapper;

import com.diviso.graeshoppe.domain.*;
import com.diviso.graeshoppe.service.dto.ContactDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Contact and its DTO ContactDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ContactAvroMapper extends AvroMapper<Contact, com.diviso.graeshoppe.avro.ContactInfo> {

	com.diviso.graeshoppe.avro.ContactInfo toAvro(com.diviso.graeshoppe.domain.Contact contact);
	

    default Contact fromId(Long id) {
        if (id == null) {
            return null;
        }
        Contact contact = new Contact();
        contact.setId(id);
        return contact;
    }
}
