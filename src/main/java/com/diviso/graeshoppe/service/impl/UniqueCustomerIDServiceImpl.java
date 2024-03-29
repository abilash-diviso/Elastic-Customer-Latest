package com.diviso.graeshoppe.service.impl;

import com.diviso.graeshoppe.service.UniqueCustomerIDService;
import com.diviso.graeshoppe.domain.UniqueCustomerID;
import com.diviso.graeshoppe.repository.UniqueCustomerIDRepository;
import com.diviso.graeshoppe.service.dto.UniqueCustomerIDDTO;
import com.diviso.graeshoppe.service.mapper.UniqueCustomerIDMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing UniqueCustomerID.
 */
@Service
@Transactional
public class UniqueCustomerIDServiceImpl implements UniqueCustomerIDService {

    private final Logger log = LoggerFactory.getLogger(UniqueCustomerIDServiceImpl.class);

    private final UniqueCustomerIDRepository uniqueCustomerIDRepository;

    private final UniqueCustomerIDMapper uniqueCustomerIDMapper;

    public UniqueCustomerIDServiceImpl(UniqueCustomerIDRepository uniqueCustomerIDRepository, UniqueCustomerIDMapper uniqueCustomerIDMapper) {
        this.uniqueCustomerIDRepository = uniqueCustomerIDRepository;
        this.uniqueCustomerIDMapper = uniqueCustomerIDMapper;
    }

    /**
     * Save a uniqueCustomerID.
     *
     * @param uniqueCustomerIDDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public UniqueCustomerIDDTO save(UniqueCustomerIDDTO uniqueCustomerIDDTO) {
        log.debug("Request to save UniqueCustomerID : {}", uniqueCustomerIDDTO);
        UniqueCustomerID uniqueCustomerID = uniqueCustomerIDMapper.toEntity(uniqueCustomerIDDTO);
        uniqueCustomerID = uniqueCustomerIDRepository.save(uniqueCustomerID);
        return uniqueCustomerIDMapper.toDto(uniqueCustomerID);
    }

    /**
     * Get all the uniqueCustomerIDS.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<UniqueCustomerIDDTO> findAll(Pageable pageable) {
        log.debug("Request to get all UniqueCustomerIDS");
        return uniqueCustomerIDRepository.findAll(pageable)
            .map(uniqueCustomerIDMapper::toDto);
    }


    /**
     * Get one uniqueCustomerID by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<UniqueCustomerIDDTO> findOne(Long id) {
        log.debug("Request to get UniqueCustomerID : {}", id);
        return uniqueCustomerIDRepository.findById(id)
            .map(uniqueCustomerIDMapper::toDto);
    }

    /**
     * Delete the uniqueCustomerID by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete UniqueCustomerID : {}", id);        uniqueCustomerIDRepository.deleteById(id);
    }
}
