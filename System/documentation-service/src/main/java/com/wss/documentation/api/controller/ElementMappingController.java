package com.wss.documentation.api.controller;


import com.wss.common.exception.ResourceNotFoundException;
import com.wss.documentation.api.request.ElementMappingCreateRequestModel;
import com.wss.documentation.api.request.ElementMappingUpdateRequestModel;
import com.wss.documentation.api.service.IElementMappingService;
import com.wss.documentationCommon.dto.ElementMappingDTO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/elementMapping")
public class ElementMappingController implements IElementMappingController
{

    private final IElementMappingService service;

    public ElementMappingController(@Qualifier("elementMappingServiceImpl") IElementMappingService elementMappingService) {
        this.service = elementMappingService;
    }

    /**
     * Get all by projectId
     * @return
     */
    public ResponseEntity<List<ElementMappingDTO>> getAllByProjectId(@PathVariable String projectId){
        List<ElementMappingDTO> elementMappingDTOS = service.findByProjectId(projectId);
        return ResponseEntity.status(HttpStatus.OK).body(elementMappingDTOS);
    }

    @Override
    public ResponseEntity<ElementMappingDTO> insert(@Valid ElementMappingCreateRequestModel request) {
        return null;
    }

    @Override
    public ResponseEntity<ElementMappingDTO> update(@Valid ElementMappingUpdateRequestModel request) throws ResourceNotFoundException {
        return null;
    }

    @Override
    public ResponseEntity<ElementMappingDTO> getById(String id) throws ResourceNotFoundException {
        ElementMappingDTO elementMappingDTO = service.findById(id);
        if(elementMappingDTO == null) throw  new ResourceNotFoundException();

        return ResponseEntity.ok(elementMappingDTO);
    }

    @Override
    public ResponseEntity<List<ElementMappingDTO>> getAll() {
        return null;
    }

    @Override
    public ResponseEntity<Void> delete(String id) throws ResourceNotFoundException {
        return null;
    }
}
