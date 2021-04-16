package com.wss.documentationTemplate.api.controller;

import com.wss.common.exception.ResourceNotFoundException;
import com.wss.documentationTemplate.api.request.DTemplateCreateRequestModel;
import com.wss.documentationTemplate.api.request.DTemplateUpdateRequestModel;
import com.wss.documentationTemplate.api.service.IDTemplateService;
import com.wss.documentationTemplate.dto.DocumentationTemplateDTO;
import com.wss.documentationTemplate.dto.ElementDocumentationTemplateDTO;
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
@RequestMapping("/dTemplate")
public class DTemplateController {

    private final IDTemplateService service;

    public DTemplateController(@Qualifier("DTemplateServiceImpl") IDTemplateService service) {
        this.service = service;
    }


    /**
     *
     * @param request
     * @return
     */
    @PostMapping(consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}, produces = { MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<DocumentationTemplateDTO> insert(@Valid @RequestBody DTemplateCreateRequestModel request){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        DocumentationTemplateDTO dto = modelMapper.map(request, DocumentationTemplateDTO.class);
        dto = service.insert(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    /**
     *
     * @param dTemplateId
     * @return
     * @throws ResourceNotFoundException
     */
    @GetMapping(value = "/dTemplate/{dTemplateId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<DocumentationTemplateDTO> getByIdWithItemList(@PathVariable("dTemplateId") String dTemplateId) throws ResourceNotFoundException {
        DocumentationTemplateDTO dTO = service.findById(dTemplateId);
        if (dTO != null)
            return ResponseEntity.status(HttpStatus.OK).body(dTO);
        else throw new ResourceNotFoundException("project not found");
    }

    /**
     *
     * @return {@link ResponseEntity}
     */
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<List<DocumentationTemplateDTO>> getAll() {
        List<DocumentationTemplateDTO> dtos = service.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }

    /**
     *
     * @param request
     * @return {@link ResponseEntity}
     */
    @PutMapping(consumes = { MediaType.APPLICATION_JSON_VALUE}, produces = {
            MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<DocumentationTemplateDTO> update(@Valid @RequestBody DTemplateUpdateRequestModel request) {
        if(service.findById(request.getId()) == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        DocumentationTemplateDTO dTO = modelMapper.map(request, DocumentationTemplateDTO.class);

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
