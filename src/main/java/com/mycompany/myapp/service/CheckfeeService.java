package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.CheckfeeDTO;
import java.util.List;

/**
 * Service Interface for managing Checkfee.
 */
public interface CheckfeeService {

    /**
     * Save a checkfee.
     *
     * @param checkfeeDTO the entity to save
     * @return the persisted entity
     */
    CheckfeeDTO save(CheckfeeDTO checkfeeDTO);

    /**
     *  Get all the checkfees.
     *
     *  @return the list of entities
     */
    List<CheckfeeDTO> findAll();

    /**
     *  Get the "id" checkfee.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    CheckfeeDTO findOne(String id);

    /**
     *  Delete the "id" checkfee.
     *
     *  @param id the id of the entity
     */
    void delete(String id);
}
