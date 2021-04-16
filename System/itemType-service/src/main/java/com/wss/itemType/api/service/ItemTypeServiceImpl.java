package com.wss.itemType.api.service;

import com.wss.itemType.api.repository.IItemTypeRepository;
import com.wss.projectElementCommon.dto.ElementTypeDTO;
import com.wss.projectElementCommon.dto.ItemTypeDTO;
import com.wss.projectElementCommon.entity.ElementTypeEntity;
import com.wss.projectElementCommon.entity.ItemTypeEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ItemTypeServiceImpl implements IItemTypeService {

    private final IItemTypeRepository repository;

    public ItemTypeServiceImpl(IItemTypeRepository repository) {
        this.repository = repository;
    }

    @Override
    public ItemTypeDTO insert(ItemTypeDTO itemTypeDTO) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        ItemTypeEntity itemTypeEntity =  modelMapper.map(itemTypeDTO, ItemTypeEntity.class);
        itemTypeEntity.setId(UUID.randomUUID().toString());
        itemTypeEntity = repository.save(itemTypeEntity);

        itemTypeDTO = modelMapper.map(itemTypeEntity, ItemTypeDTO.class);

        return itemTypeDTO;
    }

    @Override
    public ItemTypeDTO update(ItemTypeDTO itemTypeDTO) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        ItemTypeEntity itemTypeEntity = modelMapper.map(itemTypeDTO, ItemTypeEntity.class);
        itemTypeEntity = repository.save(itemTypeEntity);

        itemTypeDTO = modelMapper.map(itemTypeEntity, ItemTypeDTO.class);

        return itemTypeDTO;
    }

    @Override
    public List<ItemTypeDTO> getAll() {
        ArrayList<ItemTypeEntity> itemTypes = (ArrayList)repository.findAll();

        ModelMapper modelMapper = new ModelMapper();
        List<ItemTypeDTO> itemTypesDTO = itemTypes
                .stream()
                .map(itemTypeEntity -> modelMapper.map(itemTypeEntity, ItemTypeDTO.class))
                .collect(Collectors.toList());

        return itemTypesDTO;
    }

    @Override
    public ItemTypeDTO findById(String id) {
        ItemTypeEntity itemTypeEntity = repository.findById(id).orElse(null);

        if(itemTypeEntity == null) return null;

        ItemTypeDTO dto = new ModelMapper().map(itemTypeEntity, ItemTypeDTO.class);

        return dto;
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }
}
