package com.wss.itemList.api.service;

import com.wss.itemList.api.repository.IItemListRepository;
import com.wss.projectElementCommon.dto.ItemListDTO;
import com.wss.projectElementCommon.entity.ItemListEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Item list service implementation
 *
 * @author se7en
 */
@Service
@Transactional
public class ItemListServiceImpl implements IItemListService{

    @Autowired
    IItemListRepository repository;

    @Override
    public ItemListDTO insert(ItemListDTO itemListDTO) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        ItemListEntity itemListEntity = modelMapper.map(itemListDTO, ItemListEntity.class);
        itemListEntity.setId(UUID.randomUUID().toString());
        itemListEntity = repository.save(itemListEntity);

        itemListDTO = modelMapper.map(itemListEntity, ItemListDTO.class);

        return itemListDTO;
    }

    @Override
    public ItemListDTO update(ItemListDTO itemListDTO) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        ItemListEntity itemListEntity = modelMapper.map(itemListDTO, ItemListEntity.class);
        itemListEntity = repository.save(itemListEntity);

        itemListDTO = modelMapper.map(itemListEntity, ItemListDTO.class);

        return itemListDTO;
    }

    @Override
    public List<ItemListDTO> getAll() {
        ArrayList<ItemListEntity> itemLists = (ArrayList) repository.findAll();
        ModelMapper modelMapper = new ModelMapper();
        List<ItemListDTO> itemListsDTO = itemLists
                .stream()
                .map(itemListEntity -> modelMapper.map(itemListEntity, ItemListDTO.class))
                .collect(Collectors.toList());

        return itemListsDTO;
    }

    @Override
    public ItemListDTO findById(String id) {
        ItemListEntity itemListEntity = repository.findById(id).orElse(null);
        if(itemListEntity == null) return null;

        ItemListDTO itemListDTO = new ModelMapper().map(itemListEntity, ItemListDTO.class);

        return itemListDTO;

    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }
}
