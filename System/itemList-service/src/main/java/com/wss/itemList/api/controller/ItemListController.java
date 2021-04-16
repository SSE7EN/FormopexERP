package com.wss.itemList.api.controller;

import com.wss.common.exception.ResourceNotFoundException;
import com.wss.itemList.api.request.ItemListCreateRequestModel;
import com.wss.itemList.api.request.ItemListUpdateRequestModel;
import com.wss.itemList.api.service.IItemListService;
import com.wss.projectElementCommon.dto.ItemListDTO;
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
import java.util.List;

/**
 * Item list Controller
 *
 * @author se7en
 */
@RestController
@RequestMapping("/itemList")
public class ItemListController {

    @Qualifier("itemListServiceImpl")
    @Autowired
    private IItemListService itemListService;

    /**
     * Save item list
     * @param itemListRequest
     * @return
     */
    @PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE}, produces = { MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ItemListDTO> insert(@Valid @RequestBody ItemListCreateRequestModel itemListRequest){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        ItemListDTO itemListDTO = modelMapper.map(itemListRequest, ItemListDTO.class);

        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setId(itemListRequest.getProjectId());
        itemListDTO.setProject(projectDTO);
        itemListDTO = itemListService.insert(itemListDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(itemListDTO);
    }

    /**
     * Get itemList by id
     * @param itemListId
     * @return
     * @throws ResourceNotFoundException
     */
    @GetMapping(value = "/{itemListId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ItemListDTO> findById(@PathVariable String itemListId) throws ResourceNotFoundException{
        ItemListDTO itemListDTO = itemListService.findById(itemListId);

        if(itemListDTO == null) throw new ResourceNotFoundException("itemList not found");
        return ResponseEntity.ok(itemListDTO);

    }

    /**
     * Get All
     * @return
     */
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<List<ItemListDTO>> getAll(){
        List<ItemListDTO> itemListsDTO = itemListService.getAll();
        return ResponseEntity.ok(itemListsDTO);
    }
    /**
     * Update itemList
     * @param itemListRequest
     * @return
     * @throws ResourceNotFoundException
     */
    @PutMapping(consumes = { MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ItemListDTO> update(@Valid @RequestBody ItemListUpdateRequestModel itemListRequest) throws ResourceNotFoundException{
        if(itemListService.findById(itemListRequest.getId()) == null) throw new ResourceNotFoundException("ItemList not found");

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        ItemListDTO itemListDTO = modelMapper.map(itemListRequest, ItemListDTO.class);
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setId(itemListRequest.getProjectId());
        itemListDTO.setProject(projectDTO);

        itemListDTO = itemListService.update(itemListDTO);

        return ResponseEntity.ok(itemListDTO);
    }


    @DeleteMapping(path = "/{itemListId}")
    public ResponseEntity<Void> delete(@PathVariable String itemListId) throws ResourceNotFoundException{
        if(itemListService.findById(itemListId) == null) throw new ResourceNotFoundException("ItemList not found");

        itemListService.delete(itemListId);

        return ResponseEntity.ok().build();
    }




}
