package com.wss.elementDocumentationTemplate.api.controller;

import com.wss.common.exception.ResourceNotFoundException;
import com.wss.documentationTemplate.dto.DocumentationTemplateDTO;
import com.wss.documentationTemplate.dto.ElementDocumentationTemplateDTO;
import com.wss.documentationTemplate.dto.ItemDocumentationTemplateDTO;
import com.wss.elementDocumentationTemplate.api.request.EDTemplateCreateRequestModel;
import com.wss.elementDocumentationTemplate.api.request.EDTemplateUpdateRequestModel;
import com.wss.elementDocumentationTemplate.api.service.IEDTemplateService;
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
@RequestMapping("/eDTemplate")
public class EDTemplateController {

    private final IEDTemplateService service;

    public EDTemplateController(@Qualifier("EDTemplateServiceImpl") IEDTemplateService service) {
        this.service = service;
    }

    /**
     *
     * @param request
     * @return
     */
    @PostMapping(consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}, produces = { MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ElementDocumentationTemplateDTO> insert(@Valid @RequestBody EDTemplateCreateRequestModel request){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        ElementDocumentationTemplateDTO dto = modelMapper.map(request, ElementDocumentationTemplateDTO.class);
        DocumentationTemplateDTO documentationTemplateDTO = new DocumentationTemplateDTO();
        documentationTemplateDTO.setId(request.getdTemplateId());
        dto.setDocumentationTemplate(documentationTemplateDTO);
        dto = service.insert(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    /**
     *
     * @param eDTemplateId
     * @return
     * @throws ResourceNotFoundException
     */
    @GetMapping(value = "/eDTemplate/{eDTemplateId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ElementDocumentationTemplateDTO> getByIdWithItemList(@PathVariable("eDTemplateId") String eDTemplateId) throws ResourceNotFoundException {
        ElementDocumentationTemplateDTO dTO = service.findById(eDTemplateId);
        if (dTO != null)
            return ResponseEntity.status(HttpStatus.OK).body(dTO);
        else throw new ResourceNotFoundException("project not found");
    }

    /**
     *
     * @return {@link ResponseEntity}
     */
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<List<ElementDocumentationTemplateDTO>> getAll() {
        List<ElementDocumentationTemplateDTO> dtos = service.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }

    /**
     *
     * @param request
     * @return {@link ResponseEntity}
     */
    @PutMapping(consumes = { MediaType.APPLICATION_JSON_VALUE}, produces = {
            MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ElementDocumentationTemplateDTO> update(@Valid @RequestBody EDTemplateUpdateRequestModel request) {
        if(service.findById(request.getId()) == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        ElementDocumentationTemplateDTO dto = modelMapper.map(request, ElementDocumentationTemplateDTO.class);
        DocumentationTemplateDTO documentationTemplateDTO = new DocumentationTemplateDTO();
        documentationTemplateDTO.setId(request.getdTemplateId());
        dto.setDocumentationTemplate(documentationTemplateDTO);

        dto = service.update(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
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
