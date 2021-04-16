package com.wss.documentation.api.controller;

import com.wss.common.exception.ResourceNotFoundException;
import com.wss.documentation.api.service.IDocumentationService;
import com.wss.documentationCommon.dto.DocumentationDTO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Documentation Controller
 *
 * @author se7en
 */
@RestController
@RequestMapping("/documentation")
public class DocumentationController {


    private final IDocumentationService documentationService;

    public DocumentationController(@Qualifier("documentationServiceImpl") IDocumentationService documentationService) {
        this.documentationService = documentationService;
    }



    //    @Qualifier("documentationTypeServiceImpl")
//    @Autowired
//    IDocumentationTypeService documentationTypeService;
//
//    /**
//     * Save a documentation type
//     * @param documentationCreateRequest
//     * @return {@link ResponseEntity}
//     */
//    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
//    public ResponseEntity<DocumentationDTO> insert(@Valid @RequestBody DocumentationCreateRequest documentationCreateRequest){
//        ModelMapper modelMapper = new ModelMapper();
//        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
//        DocumentationDTO documentationDTO = modelMapper.map(documentationCreateRequest, DocumentationDTO.class);
//        documentationDTO = documentationTypeService.insert(documentationDTO);
//        return ResponseEntity.status(HttpStatus.CREATED).body(documentationDTO);
//    }
//


    /**
     * Get documentation type by id
     * @param documentationId
     * @return
     * @throws ResourceNotFoundException
     */
    @GetMapping(value = "/{documentationId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<DocumentationDTO> getById(@PathVariable String documentationId) throws ResourceNotFoundException{
        DocumentationDTO documentationDTO = documentationService.findById(documentationId);
        if(documentationDTO == null)
            throw new ResourceNotFoundException("id was not found");
        return ResponseEntity.status(HttpStatus.OK).body(documentationDTO);
    }

    /**
     * Get all rows
     * @return
     */
    @GetMapping
    public ResponseEntity<List<DocumentationDTO>> getAll(){
        List<DocumentationDTO> documentationTypesDTO = documentationService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(documentationTypesDTO);
    }
//
//    /**
//     * Update documentation type
//     * @param documentationUpdateRequest
//     * @return {@link ResponseEntity}
//     * @throws ResourceNotFoundException
//     */
//    @PutMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
//    public ResponseEntity<DocumentationDTO> update(@Valid @RequestBody DocumentationUpdateRequest documentationUpdateRequest) throws ResourceNotFoundException{
//        if(documentationTypeService.findById(documentationUpdateRequest.getId()) == null)
//            throw new ResourceNotFoundException("id was not found");
//
//        ModelMapper modelMapper = new ModelMapper();
//        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
//
//        DocumentationDTO documentationDTO = modelMapper.map(documentationUpdateRequest, DocumentationDTO.class);
//        documentationDTO = documentationTypeService.update(documentationDTO);
//
//        return ResponseEntity.status(HttpStatus.OK).body(documentationDTO);
//    }
//
//    /**
//     * Delete documentation type by id
//     * @param documentationId
//     * @return
//     * @throws ResourceNotFoundException
//     */
//    @DeleteMapping(path = "/{documentationId}")
//    public ResponseEntity<Void> delete(@PathVariable String documentationId) throws ResourceNotFoundException{
//        if(documentationTypeService.findById(documentationId) == null)
//            throw new ResourceNotFoundException("id was not found");
//
//        documentationTypeService.delete(documentationId);
//        return ResponseEntity.ok().build();
//    }






}
