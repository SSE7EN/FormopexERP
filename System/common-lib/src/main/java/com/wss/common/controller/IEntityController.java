package com.wss.common.controller;

import com.wss.common.exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

public interface IEntityController<DTO, RCM, RUM> {

    /**
     * Insert
     * @param request
     * @return
     */
    @PostMapping(consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}, produces = { MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<DTO> insert(@Valid @RequestBody RCM request);

    /**
     * Update
     * @param request
     * @return
     * @throws ResourceNotFoundException
     */
    @PutMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, produces = {
            MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<DTO> update(@Valid @RequestBody RUM request) throws ResourceNotFoundException;

    /**
     * Get by id
     * @param id
     * @return
     * @throws ResourceNotFoundException
     */
    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<DTO> getById(@PathVariable("id") String id) throws ResourceNotFoundException;

    /**
     * Get all
     * @return
     */
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<List<DTO>> getAll();

    /**
     * Delete by id
     * @param id
     * @return
     * @throws ResourceNotFoundException
     */
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) throws ResourceNotFoundException;
}
