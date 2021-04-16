package com.wss.itemType.api.controller;

import com.wss.common.exception.ResourceNotFoundException;
import com.wss.itemType.api.request.ItemTypeCreateRequestModel;
import com.wss.itemType.api.request.ItemTypeUpdateRequestModel;
import com.wss.itemType.api.service.IItemTypeService;
import com.wss.projectElementCommon.dto.ElementTypeDTO;
import com.wss.projectElementCommon.dto.ItemTypeDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/itemType")
public class ItemTypeController {

    private final IItemTypeService service;

    public ItemTypeController( @Qualifier("itemTypeServiceImpl") IItemTypeService service) {
        this.service = service;
    }

    /**
     * Save an itemType
     * @param request
     * @return {@link ResponseEntity}
     */
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ItemTypeDTO> insert(@Valid @RequestBody ItemTypeCreateRequestModel request){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        ItemTypeDTO dto = modelMapper.map(request, ItemTypeDTO.class);

        dto = service.insert(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    /**
     * Get itemType by id
     * @param itemTypeId
     * @return
     * @throws ResourceNotFoundException
     */
    @GetMapping(value = "/{itemTypeId}", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ItemTypeDTO> getById(@PathVariable String itemTypeId) throws ResourceNotFoundException{
        ItemTypeDTO dto = service.findById(itemTypeId);

        if(dto != null)
            return ResponseEntity.status(HttpStatus.OK).body(dto);
        else throw new ResourceNotFoundException("item type not found");
    }

    /**
     * Get all elementTypes
     * @return {@link ResponseEntity}
     */
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<ItemTypeDTO>> getAll(){
        List<ItemTypeDTO> dtos = (ArrayList)service.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }

    /**
     * Update an itemType
     * @param request
     * @return {@link ResponseEntity}
     * @throws ResourceNotFoundException
     */
    @PutMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ItemTypeDTO> update(@Valid @RequestBody ItemTypeUpdateRequestModel request) throws ResourceNotFoundException{
        if(service.findById(request.getId()) == null)
            throw new ResourceNotFoundException("element not found");

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        ItemTypeDTO dto = modelMapper.map(request, ItemTypeDTO.class);


        dto = service.insert(dto);

        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @DeleteMapping(path = "/{itemTypeId}")
    public ResponseEntity<Void> delete(@PathVariable String itemTypeId) throws ResourceNotFoundException{
        if(service.findById(itemTypeId) == null)
            throw  new ResourceNotFoundException("Item type not found");

        service.delete(itemTypeId);
        return ResponseEntity.ok().build();

    }

}
