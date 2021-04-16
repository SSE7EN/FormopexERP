package com.wss.itemList.api.service;

import com.wss.itemList.api.repository.IItemRepository;
import com.wss.projectElementCommon.dto.ItemDTO;
import com.wss.projectElementCommon.entity.ItemEntity;
import org.checkerframework.checker.units.qual.A;
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
 * Item service interface implementation
 *
 * @author se7en
 */
@Service
@Transactional
public class ItemServiceImpl implements IItemService {

    @Autowired
    IItemRepository repository;

    @Override
    public ItemDTO insert(ItemDTO itemDTO) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        ItemEntity itemEntity = modelMapper.map(itemDTO, ItemEntity.class);
        itemEntity.setId(UUID.randomUUID().toString());
        itemEntity = repository.save(itemEntity);

        itemDTO = modelMapper.map(itemEntity, ItemDTO.class);
        return itemDTO;

    }

    @Override
    public ItemDTO update(ItemDTO itemDTO) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        ItemEntity itemEntity = modelMapper.map(itemDTO, ItemEntity.class);
        itemEntity = repository.save(itemEntity);

        itemDTO = modelMapper.map(itemEntity, ItemDTO.class);
        return itemDTO;
    }

    @Override
    public List<ItemDTO> getAll() {
        ArrayList<ItemDTO> items = (ArrayList)repository.findAll();

        ModelMapper modelMapper = new ModelMapper();
        List<ItemDTO> itemsDTO = items
                .stream()
                .map(item -> modelMapper.map(item, ItemDTO.class))
                .collect(Collectors.toList());

        return itemsDTO;
    }

    @Override
    public ItemDTO findById(String id) {
        ItemEntity itemEntity = repository.findById(id).orElse(null);

        if(itemEntity == null) return null;

        ItemDTO itemDTO = new ModelMapper().map(itemEntity, ItemDTO.class);
        return itemDTO;
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);

    }
}
