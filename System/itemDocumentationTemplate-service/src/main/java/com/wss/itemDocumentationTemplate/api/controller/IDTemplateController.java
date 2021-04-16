package com.wss.itemDocumentationTemplate.api.controller;

import com.wss.common.exception.ResourceNotFoundException;
import com.wss.documentationTemplate.dto.DocumentationTemplateDTO;
import com.wss.documentationTemplate.dto.ItemDocumentationTemplateDTO;
import com.wss.documentationTemplate.entity.EItemDocumentationType;
import com.wss.itemDocumentationTemplate.api.request.IDTemplateCreateRequestModel;
import com.wss.itemDocumentationTemplate.api.request.IDTemplateUpdateRequestModel;
import com.wss.itemDocumentationTemplate.api.service.IIDTemplateService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/iDTemplate")
public class IDTemplateController {

    private final IIDTemplateService service;

    public IDTemplateController(@Qualifier("IDTemplateServiceImpl") IIDTemplateService service) {
        this.service = service;
    }

    /**
     *
     * @param request
     * @return
     */
    @PostMapping(consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}, produces = { MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ItemDocumentationTemplateDTO> insert(@Valid @RequestBody IDTemplateCreateRequestModel request){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        ItemDocumentationTemplateDTO dto = modelMapper.map(request, ItemDocumentationTemplateDTO.class);
        DocumentationTemplateDTO documentationTemplateDTO = new DocumentationTemplateDTO();
        documentationTemplateDTO.setId(request.getdTemplateId());
        dto.setDocumentationTemplate(documentationTemplateDTO);
        dto = service.insert(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    /**
     *
     * @param iDTemplateId
     * @return
     * @throws ResourceNotFoundException
     */
    @GetMapping(value = "/iDTemplate/{iDTemplateId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ItemDocumentationTemplateDTO> getByIdWithItemList(@PathVariable("iDTemplateId") String iDTemplateId) throws ResourceNotFoundException {
        ItemDocumentationTemplateDTO dTO = service.findById(iDTemplateId);
        if (dTO != null)
            return ResponseEntity.status(HttpStatus.OK).body(dTO);
        else throw new ResourceNotFoundException("project not found");
    }

    @GetMapping(value = "/iDTemplate/itemDocumentationTypes", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<EItemDocumentationType>> getAllItemDocumentationTypes() throws ResourceNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAllItemDocumentationTypes());
    }

    /**
     *
     * @return {@link ResponseEntity}
     */
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<List<ItemDocumentationTemplateDTO>> getAll() {
        List<ItemDocumentationTemplateDTO> dtos = service.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }

    /**
     *
     * @param request
     * @return {@link ResponseEntity}
     */
    @PutMapping(consumes = { MediaType.APPLICATION_JSON_VALUE}, produces = {
            MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ItemDocumentationTemplateDTO> update(@Valid @RequestBody IDTemplateUpdateRequestModel request) {
        if(service.findById(request.getId()) == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        ItemDocumentationTemplateDTO dTO = modelMapper.map(request, ItemDocumentationTemplateDTO.class);

        dTO = service.update(dTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(dTO);
    }

    /**
     * Delete
     * @return
     * @throws
     */
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        if(service.findById(id) == null)
            return ResponseEntity.notFound().build();

        service.delete(id);
        return ResponseEntity.ok().build();
    }



}
