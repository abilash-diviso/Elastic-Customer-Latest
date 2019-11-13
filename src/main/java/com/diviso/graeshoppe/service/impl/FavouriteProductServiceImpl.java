package com.diviso.graeshoppe.service.impl;

import com.diviso.graeshoppe.service.FavouriteProductService;
import com.diviso.graeshoppe.domain.FavouriteProduct;
import com.diviso.graeshoppe.repository.FavouriteProductRepository;
import com.diviso.graeshoppe.service.dto.FavouriteProductDTO;
import com.diviso.graeshoppe.service.mapper.FavouriteProductMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing FavouriteProduct.
 */
@Service
@Transactional
public class FavouriteProductServiceImpl implements FavouriteProductService {

    private final Logger log = LoggerFactory.getLogger(FavouriteProductServiceImpl.class);

    private final FavouriteProductRepository favouriteProductRepository;

    private final FavouriteProductMapper favouriteProductMapper;

    public FavouriteProductServiceImpl(FavouriteProductRepository favouriteProductRepository, FavouriteProductMapper favouriteProductMapper) {
        this.favouriteProductRepository = favouriteProductRepository;
        this.favouriteProductMapper = favouriteProductMapper;
    }

    /**
     * Save a favouriteProduct.
     *
     * @param favouriteProductDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public FavouriteProductDTO save(FavouriteProductDTO favouriteProductDTO) {
        log.debug("Request to save FavouriteProduct : {}", favouriteProductDTO);
        FavouriteProduct favouriteProduct = favouriteProductMapper.toEntity(favouriteProductDTO);
        favouriteProduct = favouriteProductRepository.save(favouriteProduct);
        return favouriteProductMapper.toDto(favouriteProduct);
    }

    /**
     * Get all the favouriteProducts.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<FavouriteProductDTO> findAll(Pageable pageable) {
        log.debug("Request to get all FavouriteProducts");
        return favouriteProductRepository.findAll(pageable)
            .map(favouriteProductMapper::toDto);
    }


    /**
     * Get one favouriteProduct by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<FavouriteProductDTO> findOne(Long id) {
        log.debug("Request to get FavouriteProduct : {}", id);
        return favouriteProductRepository.findById(id)
            .map(favouriteProductMapper::toDto);
    }

    /**
     * Delete the favouriteProduct by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete FavouriteProduct : {}", id);        favouriteProductRepository.deleteById(id);
    }
}
