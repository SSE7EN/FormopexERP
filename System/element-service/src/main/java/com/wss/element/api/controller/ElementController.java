package com.wss.element.api.controller;

import com.wss.common.exception.ResourceNotFoundException;
import com.wss.element.api.request.ElementCreateRequestModel;
import com.wss.element.api.request.ElementUpdateRequestModel;
import com.wss.element.api.service.IElementService;
import com.wss.projectElementCommon.dto.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Element Controller
 *
 * @author se7en
 */
@RestController
@RequestMapping(path = "/element")
public class ElementController {

    @Qualifier("elementServiceImpl")
    @Autowired
    private IElementService elementService;

    /**
     * Save an element
     * @param elementRequest
     * @return {@link ResponseEntity}
     */
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<ElementDTO>> insert(@Valid @RequestBody ElementCreateRequestModel elementRequest){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        ElementWithProjectDTO elementDTO = modelMapper.map(elementRequest, ElementWithProjectDTO.class);

        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setId(elementRequest.getProjectId());
        elementDTO.setProject(projectDTO);

        ItemListDTO itemListDTO = new ItemListDTO();
        itemListDTO.setId(elementRequest.getItemListId());
        elementDTO.setItemList(itemListDTO);



        if(elementRequest.getElementTypeId() != "") {
            ElementTypeDTO elementTypeDTO = new ElementTypeDTO();
            elementTypeDTO.setId(elementRequest.getElementTypeId());
            elementDTO.setElementType(elementTypeDTO);
        }

        List<ElementDTO> elementDTOS = elementService.insert(elementDTO, elementRequest.getElementCount());

        return ResponseEntity.status(HttpStatus.CREATED).body(elementDTOS);
    }

    /**
     * Get element by id
     * @param elementId
     * @return
     * @throws ResourceNotFoundException
     */
    @GetMapping(value = "/{elementId}", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ElementDTO> getById(@PathVariable String elementId) throws ResourceNotFoundException{
        ElementDTO elementDTO = elementService.findById(elementId);

        if(elementDTO != null)
            return ResponseEntity.status(HttpStatus.OK).body(elementDTO);
        else throw new ResourceNotFoundException("element not found");
    }

    /**
     * Get all elements
     * @return {@link ResponseEntity}
     */
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<ElementDTO>> getAll(){
        List<ElementDTO> elementsDTO = (ArrayList)elementService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(elementsDTO);
    }

    /**
     * Update an element
     * @param elementRequest
     * @return {@link ResponseEntity}
     * @throws ResourceNotFoundException
     */
    @PutMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ElementWithProjectDTO> update(@Valid @RequestBody ElementUpdateRequestModel elementRequest) throws ResourceNotFoundException{
        if(elementService.findById(elementRequest.getId()) == null)
            throw new ResourceNotFoundException("Element not found");

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        ElementWithProjectDTO elementDTO = modelMapper.map(elementRequest, ElementWithProjectDTO.class);

        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setId(elementRequest.getProjectId());
        elementDTO.setProject(projectDTO);

        if(elementRequest.getElementTypeId() != null) {
            ElementTypeDTO elementTypeDTO = new ElementTypeDTO();
            elementTypeDTO.setId(elementRequest.getElementTypeId());
            elementDTO.setElementType(elementTypeDTO);
        }

        elementDTO = elementService.update(elementDTO);

        return ResponseEntity.status(HttpStatus.OK).body(elementDTO);
    }

    @DeleteMapping(path = "/{elementId}")
    public ResponseEntity<Void> delete(@PathVariable String elementId) throws ResourceNotFoundException{
        if(elementService.findById(elementId) == null)
            throw  new ResourceNotFoundException("Element not found");

        elementService.delete(elementId);
        return ResponseEntity.ok().build();

    }

}
