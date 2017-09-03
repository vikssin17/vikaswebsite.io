package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.CheckfeeService;
import com.mycompany.myapp.domain.Checkfee;
import com.mycompany.myapp.repository.CheckfeeRepository;
import com.mycompany.myapp.service.dto.CheckfeeDTO;
import com.mycompany.myapp.service.mapper.CheckfeeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Checkfee.
 */
@Service
public class CheckfeeServiceImpl implements CheckfeeService{

    private final Logger log = LoggerFactory.getLogger(CheckfeeServiceImpl.class);

    private final CheckfeeRepository checkfeeRepository;

    private final CheckfeeMapper checkfeeMapper;

    public CheckfeeServiceImpl(CheckfeeRepository checkfeeRepository, CheckfeeMapper checkfeeMapper) {
        this.checkfeeRepository = checkfeeRepository;
        this.checkfeeMapper = checkfeeMapper;
    }

    /**
     * Save a checkfee.
     *
     * @param checkfeeDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public CheckfeeDTO save(CheckfeeDTO checkfeeDTO) {
        log.debug("Request to save Checkfee : {}", checkfeeDTO);
        Checkfee checkfee = checkfeeMapper.toEntity(checkfeeDTO);
        checkfee = checkfeeRepository.save(checkfee);
        return checkfeeMapper.toDto(checkfee);
    }

    /**
     *  Get all the checkfees.
     *
     *  @return the list of entities
     */
    @Override
    public List<CheckfeeDTO> findAll() {
        log.debug("Request to get all Checkfees");
        return checkfeeRepository.findAll().stream()
            .map(checkfeeMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     *  Get one checkfee by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    public CheckfeeDTO findOne(String id) {
        log.debug("Request to get Checkfee : {}", id);
        Checkfee checkfee = checkfeeRepository.findOne(UUID.fromString(id));
        return checkfeeMapper.toDto(checkfee);
    }

    /**
     *  Delete the  checkfee by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Checkfee : {}", id);
        checkfeeRepository.delete(UUID.fromString(id));
    }
}
