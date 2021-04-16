package com.wss.documentationProvider.api.controller;

import com.wss.common.controller.IEntityController;
import com.wss.common.exception.ResourceNotFoundException;
import com.wss.common.util.Util;
import com.wss.documentationCommon.dto.DocumentationProviderDTO;
import com.wss.documentationProvider.api.request.DocumentationProviderCreateRequestModel;
import com.wss.documentationProvider.api.request.DocumentationProviderUpdateRequestModel;
import com.wss.documentationProvider.api.service.IDocumentationProviderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/documentationProvider")
public class DocumentationProviderController implements IEntityController<DocumentationProviderDTO, DocumentationProviderCreateRequestModel, DocumentationProviderUpdateRequestModel> {

    private final IDocumentationProviderService service;

    public DocumentationProviderController(@Qualifier("documentationProviderServiceImpl") IDocumentationProviderService service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<DocumentationProviderDTO> insert(@Valid DocumentationProviderCreateRequestModel request) {
        ModelMapper modelMapper = Util.modelMapperStrict();
        DocumentationProviderDTO dto = modelMapper.map(request, DocumentationProviderDTO.class);
        dto = service.insert(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(dto);

    }

    @Override
    public ResponseEntity<DocumentationProviderDTO> update(@Valid DocumentationProviderUpdateRequestModel request) throws ResourceNotFoundException {
        return null;
    }

    @Override
    public ResponseEntity<DocumentationProviderDTO> getById(String id) throws ResourceNotFoundException {
        DocumentationProviderDTO dto = service.findById(id);

        if(dto == null) throw new ResourceNotFoundException();

        return ResponseEntity.ok().body(dto);
    }

    @Override
    public ResponseEntity<List<DocumentationProviderDTO>> getAll() {
        List<DocumentationProviderDTO> documentationProviderDTOS = service.getAll();
        return ResponseEntity.ok(documentationProviderDTOS);

    }

    @Override
    public ResponseEntity<Void> delete(String id) throws ResourceNotFoundException {
        return null;
    }
}
