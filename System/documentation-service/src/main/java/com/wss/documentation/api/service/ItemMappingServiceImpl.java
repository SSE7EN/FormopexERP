package com.wss.documentation.api.service;


import com.wss.documentation.api.repository.IItemMappingRepository;
import com.wss.documentationCommon.dto.ItemMappingDTO;
import com.wss.documentationCommon.entity.ItemMappingEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class ItemMappingServiceImpl implements IItemMappingService {

    private final IItemMappingRepository repository;

    public ItemMappingServiceImpl(IItemMappingRepository repository) {
        this.repository = repository;
    }

    @Override
    public ItemMappingDTO insert(ItemMappingDTO dto) {

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        ItemMappingEntity entity = modelMapper.map(dto, ItemMappingEntity.class);
        entity.setId(UUID.randomUUID().toString());

        entity = repository.save(entity);

        dto = modelMapper.map(entity, ItemMappingDTO.class);
        return dto;
    }


    @Override
    public ItemMappingDTO update(ItemMappingDTO dto) {
        return null;
    }

    @Override
    public List<ItemMappingDTO> getAll() {
        return null;
    }

    @Override
    public ItemMappingDTO findById(String id) {
        return null;
    }

    @Override
    public void delete(String id) {

    }
}

