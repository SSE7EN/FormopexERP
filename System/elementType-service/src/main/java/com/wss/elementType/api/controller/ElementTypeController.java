package com.wss.elementType.api.controller;

import com.wss.common.exception.ResourceNotFoundException;
import com.wss.elementType.api.request.ElementTypeCreateRequestModel;
import com.wss.elementType.api.request.ElementTypeUpdateRequestModel;
import com.wss.elementType.api.service.IElementTypeService;
import com.wss.projectElementCommon.dto.ElementDTO;
import com.wss.projectElementCommon.dto.ElementTypeDTO;
import com.wss.projectElementCommon.dto.ElementWithProjectDTO;
import com.wss.projectElementCommon.dto.ProjectDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.annotation.ElementType;
import java.util.ArrayList;
import java.util.List;

/**
 * ElementType Controller
 *
 * @author se7en
 */
@RestController
@RequestMapping("/elementType")
public class ElementTypeController {

    @Qualifier("elementTypeServiceImpl")
    @Autowired
    private IElementTypeService elementTypeService;



    /**
     * Save an elementType
     * @param elementRequest
     * @return {@link ResponseEntity}
     */
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ElementTypeDTO> insert(@Valid @RequestBody ElementTypeCreateRequestModel elementRequest){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        ElementTypeDTO elementDTO = modelMapper.map(elementRequest, ElementTypeDTO.class);

        elementDTO = elementTypeService.insert(elementDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(elementDTO);
    }

    /**
     * Get elementType by id
     * @param elementTypeId
     * @return
     * @throws ResourceNotFoundException
     */
    @GetMapping(value = "/{elementTypeId}", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ElementTypeDTO> getById(@PathVariable String elementTypeId) throws ResourceNotFoundException{
        ElementTypeDTO elementTypeDTO = elementTypeService.findById(elementTypeId);

        if(elementTypeDTO != null)
            return ResponseEntity.status(HttpStatus.OK).body(elementTypeDTO);
        else throw new ResourceNotFoundException("element not found");
    }

    /**
     * Get all elementTypes
     * @return {@link ResponseEntity}
     */
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<ElementTypeDTO>> getAll(){
        List<ElementTypeDTO> elementsDTO = (ArrayList)elementTypeService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(elementsDTO);
    }

    /**
     * Update an element
     * @param elementRequest
     * @return {@link ResponseEntity}
     * @throws ResourceNotFoundException
     */
    @PutMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ElementTypeDTO> update(@Valid @RequestBody ElementTypeUpdateRequestModel elementRequest) throws ResourceNotFoundException{
        if(elementTypeService.findById(elementRequest.getId()) == null)
            throw new ResourceNotFoundException("element not found");

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        ElementTypeDTO elementTypeDTO = modelMapper.map(elementRequest, ElementTypeDTO.class);


        elementTypeDTO = elementTypeService.insert(elementTypeDTO);

        return ResponseEntity.status(HttpStatus.OK).body(elementTypeDTO);
    }

    @DeleteMapping(path = "/{elementId}")
    public ResponseEntity<Void> delete(@PathVariable String elementId) throws ResourceNotFoundException{
        if(elementTypeService.findById(elementId) == null)
            throw  new ResourceNotFoundException("Element type not found");

        elementTypeService.delete(elementId);
        return ResponseEntity.ok().build();

    }


}
