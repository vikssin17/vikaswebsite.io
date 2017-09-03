package com.mycompany.myapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.mycompany.myapp.service.CheckfeeService;
import com.mycompany.myapp.web.rest.util.HeaderUtil;
import com.mycompany.myapp.service.dto.CheckfeeDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * REST controller for managing Checkfee.
 */
@RestController
@RequestMapping("/api")
public class CheckfeeResource {

    private final Logger log = LoggerFactory.getLogger(CheckfeeResource.class);

    private static final String ENTITY_NAME = "checkfee";

    private final CheckfeeService checkfeeService;

    public CheckfeeResource(CheckfeeService checkfeeService) {
        this.checkfeeService = checkfeeService;
    }

    /**
     * POST  /checkfees : Create a new checkfee.
     *
     * @param checkfeeDTO the checkfeeDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new checkfeeDTO, or with status 400 (Bad Request) if the checkfee has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/checkfees")
    @Timed
    public ResponseEntity<CheckfeeDTO> createCheckfee(@RequestBody CheckfeeDTO checkfeeDTO) throws URISyntaxException {
        log.debug("REST request to save Checkfee : {}", checkfeeDTO);
        if (checkfeeDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new checkfee cannot already have an ID")).body(null);
        }
        CheckfeeDTO result = checkfeeService.save(checkfeeDTO);
        return ResponseEntity.created(new URI("/api/checkfees/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /checkfees : Updates an existing checkfee.
     *
     * @param checkfeeDTO the checkfeeDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated checkfeeDTO,
     * or with status 400 (Bad Request) if the checkfeeDTO is not valid,
     * or with status 500 (Internal Server Error) if the checkfeeDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/checkfees")
    @Timed
    public ResponseEntity<CheckfeeDTO> updateCheckfee(@RequestBody CheckfeeDTO checkfeeDTO) throws URISyntaxException {
        log.debug("REST request to update Checkfee : {}", checkfeeDTO);
        if (checkfeeDTO.getId() == null) {
            return createCheckfee(checkfeeDTO);
        }
        CheckfeeDTO result = checkfeeService.save(checkfeeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, checkfeeDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /checkfees : get all the checkfees.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of checkfees in body
     */
    @GetMapping("/checkfees")
    @Timed
    public List<CheckfeeDTO> getAllCheckfees() {
        log.debug("REST request to get all Checkfees");
        return checkfeeService.findAll();
    }

    /**
     * GET  /checkfees/:id : get the "id" checkfee.
     *
     * @param id the id of the checkfeeDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the checkfeeDTO, or with status 404 (Not Found)
     */
    @GetMapping("/checkfees/{id}")
    @Timed
    public ResponseEntity<CheckfeeDTO> getCheckfee(@PathVariable String id) {
        log.debug("REST request to get Checkfee : {}", id);
        CheckfeeDTO checkfeeDTO = checkfeeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(checkfeeDTO));
    }

    /**
     * DELETE  /checkfees/:id : delete the "id" checkfee.
     *
     * @param id the id of the checkfeeDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/checkfees/{id}")
    @Timed
    public ResponseEntity<Void> deleteCheckfee(@PathVariable String id) {
        log.debug("REST request to delete Checkfee : {}", id);
        checkfeeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }
}
