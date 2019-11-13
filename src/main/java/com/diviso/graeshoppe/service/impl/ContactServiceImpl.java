package com.diviso.graeshoppe.service.impl;

import com.diviso.graeshoppe.service.ContactService;
import com.diviso.graeshoppe.config.MessageBinderConfiguration;
import com.diviso.graeshoppe.domain.Contact;
import com.diviso.graeshoppe.repository.ContactRepository;
import com.diviso.graeshoppe.service.dto.ContactDTO;
import com.diviso.graeshoppe.service.mapper.ContactAvroMapper;
import com.diviso.graeshoppe.service.mapper.ContactMapper;
import com.diviso.graeshoppe.service.mapper.CustomerAvroMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Contact.
 */
@Service
@Transactional
public class ContactServiceImpl implements ContactService {

    private final Logger log = LoggerFactory.getLogger(ContactServiceImpl.class);

    private final ContactRepository contactRepository;

    private final ContactMapper contactMapper;
    
	private final ContactAvroMapper contactAvroMapper;
	
	@Autowired
	private MessageBinderConfiguration messageChannel;

    public ContactServiceImpl(ContactRepository contactRepository, ContactMapper contactMapper, ContactAvroMapper contactAvroMapper) {
        this.contactRepository = contactRepository;
        this.contactMapper = contactMapper;
        this.contactAvroMapper = contactAvroMapper;
    }

    /**
     * Save a contact.
     *
     * @param contactDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ContactDTO save(ContactDTO contactDTO) {
        log.debug("Request to save Contact : {}", contactDTO);
        Contact contact = contactMapper.toEntity(contactDTO);
        contact = contactRepository.save(contact);
        ContactDTO result= contactMapper.toDto(contact);
        
		String status="create";
        boolean publishstatus=createPublishMesssage(contact,status);
        
        log.debug("------------------------------------------published"+publishstatus);
        return result;
        
    }
    
	@Override
	public boolean createPublishMesssage(com.diviso.graeshoppe.domain.Contact contact, String status) {
		
        log.debug("------------------------------------------publish method"+status);

		com.diviso.graeshoppe.avro.ContactInfo message =contactAvroMapper.toAvro(contact);
		message .setStatus(status);

		System.out.println("avro mapped#############################################"+message);

		return messageChannel.contactOut().send(MessageBuilder.withPayload(message).build());
		

	}
    
    

    /**
     * Get all the contacts.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ContactDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Contacts");
        return contactRepository.findAll(pageable)
            .map(contactMapper::toDto);
    }


    /**
     * Get one contact by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ContactDTO> findOne(Long id) {
        log.debug("Request to get Contact : {}", id);
        return contactRepository.findById(id)
            .map(contactMapper::toDto);
    }

    /**
     * Delete the contact by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Contact : {}", id);        contactRepository.deleteById(id);
    }
}
