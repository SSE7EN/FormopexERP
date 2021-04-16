package com.wss.itemList.api.controller;

import com.wss.common.exception.ResourceNotFoundException;
import com.wss.itemList.api.request.ItemCreateRequestModel;
import com.wss.itemList.api.request.ItemUpdateRequestModel;
import com.wss.itemList.api.service.IItemService;
import com.wss.projectElementCommon.dto.ItemDTO;
import com.wss.projectElementCommon.dto.ItemListDTO;
import com.wss.projectElementCommon.dto.ItemTypeDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Item Controller
 *
 * @author se7en
 */
@RestController
@RequestMapping(path = "/item")
public class ItemController {

    @Qualifier("itemServiceImpl")
    @Autowired
    private IItemService itemService;


    /**
     * Save item
     * @param itemRequest
     * @return
     */
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ItemDTO> insert(@Valid @RequestBody ItemCreateRequestModel itemRequest){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        ItemDTO itemDTO = modelMapper.map(itemRequest, ItemDTO.class);
        ItemListDTO itemListDTO = new ItemListDTO();
        itemListDTO.setId(itemRequest.getItemListId());
        itemDTO.setItemList(itemListDTO);
        if(itemRequest.getItemTypeId() != null){
            ItemTypeDTO itemTypeDTO = new ItemTypeDTO();
            itemTypeDTO.setId(itemRequest.getItemTypeId());
            itemDTO.setItemType(itemTypeDTO);
        }



        itemDTO = itemService.insert(itemDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(itemDTO);

    }


    /**
     * Get item by id
     * @param itemId
     * @return
     * @throws ResourceNotFoundException
     */
    @GetMapping(value = "/{itemId}", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ItemDTO> getById(@PathVariable String itemId) throws ResourceNotFoundException{
        ItemDTO itemDTO = itemService.findById(itemId);

        if(itemDTO == null) throw  new ResourceNotFoundException("item not found");

        return ResponseEntity.ok(itemDTO);
    }


    /**
     * Get all items
     * @return
     */
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<ItemDTO>> getAll(){
        List<ItemDTO> itemsDTO = (ArrayList)itemService.getAll();
        return ResponseEntity.ok(itemsDTO);
    }

    @PutMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ItemDTO> update(@Valid @RequestBody ItemUpdateRequestModel itemRequest) throws ResourceNotFoundException{
        if(itemService.findById(itemRequest.getId()) == null)
            throw new ResourceNotFoundException("Item not found");

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        ItemDTO itemDTO = modelMapper.map(itemRequest, ItemDTO.class);

        ItemListDTO itemListDTO = new ItemListDTO();
        itemListDTO.setId(itemRequest.getItemListId());
        itemDTO.setItemList(itemListDTO);

        itemDTO = itemService.update(itemDTO);

        return ResponseEntity.ok(itemDTO);

    }

    @DeleteMapping(path = "/{itemId}")
    public ResponseEntity<Void> delete(@PathVariable String itemId) throws ResourceNotFoundException{
        if(itemService.findById(itemId) == null) throw new ResourceNotFoundException("Item not found");

        itemService.delete(itemId);
        return ResponseEntity.ok().build();
    }

}
