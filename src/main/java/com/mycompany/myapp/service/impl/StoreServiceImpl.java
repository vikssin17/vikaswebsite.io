package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.StoreService;
import com.mycompany.myapp.domain.Store;
import com.mycompany.myapp.repository.StoreRepository;
import com.mycompany.myapp.service.dto.StoreDTO;
import com.mycompany.myapp.service.mapper.StoreMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Store.
 */
@Service
public class StoreServiceImpl implements StoreService{

    private final Logger log = LoggerFactory.getLogger(StoreServiceImpl.class);

    private final StoreRepository storeRepository;

    private final StoreMapper storeMapper;

    public StoreServiceImpl(StoreRepository storeRepository, StoreMapper storeMapper) {
        this.storeRepository = storeRepository;
        this.storeMapper = storeMapper;
    }

    /**
     * Save a store.
     *
     * @param storeDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public StoreDTO save(StoreDTO storeDTO) {
        log.debug("Request to save Store : {}", storeDTO);
        Store store = storeMapper.toEntity(storeDTO);
        store = storeRepository.save(store);
        return storeMapper.toDto(store);
    }

    /**
     *  Get all the stores.
     *
     *  @return the list of entities
     */
    @Override
    public List<StoreDTO> findAll() {
        log.debug("Request to get all Stores");
        return storeRepository.findAll().stream()
            .map(storeMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     *  Get one store by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    public StoreDTO findOne(String id) {
        log.debug("Request to get Store : {}", id);
        Store store = storeRepository.findOne(UUID.fromString(id));
        return storeMapper.toDto(store);
    }

    /**
     *  Delete the  store by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Store : {}", id);
        storeRepository.delete(UUID.fromString(id));
    }
}
