package com.wss.documentation.api.service;

import com.wss.common.exception.FileCreateException;
import com.wss.common.storage.StorageProperties;
import com.wss.common.storage.service.IStorageService;
import com.wss.common.util.Util;
import com.wss.documentation.api.repository.IItemDocumentRepository;
import com.wss.documentationCommon.dto.ItemDocumentDTO;
import com.wss.documentationCommon.entity.ItemDocumentEntity;
import com.wss.documentationCommon.entity.ItemMappingEntity;
import com.wss.documentationTemplate.entity.EItemDocumentationType;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class ItemDocumentServiceImpl implements IItemDocumentService {

    private final IItemDocumentRepository repository;
    private final IStorageService storageService;
    private final StorageProperties storageProperties;

    public ItemDocumentServiceImpl(IItemDocumentRepository repository, IStorageService storageService, StorageProperties storageProperties) {
        this.repository = repository;
        this.storageService = storageService;
        this.storageProperties = storageProperties;
    }

    @Override
    public ItemDocumentDTO insert(ItemDocumentDTO dto, EItemDocumentationType type) {
        String path = storageProperties.getLocation();
        if(type == null){
            path =  "/"+dto.getDocumentation().getName()+"/"+dto.getItemMappings().get(0).getElementMapping().getName()+"/"+dto.getName();
            storageService.createFile(path);
            dto.setPath(path);
            dto = insertUnique(dto);
            return dto;
        }
        switch(type){
            case COMMON:
                path = "/"+dto.getDocumentation().getName()+"/common/"+dto.getName();
                storageService.createFile(path);
                dto.setPath(path);
                dto = insertCommon(dto);
                break;
            case UNIQUE:
                path =  "/"+dto.getDocumentation().getName()+"/"+dto.getItemMappings().get(0).getElementMapping().getName()+"/"+dto.getName();
                storageService.createFile(path);
                dto.setPath(path);
                dto = insertUnique(dto);
                break;
        }
        return dto;
    }

    @Override
    public ItemDocumentDTO update(ItemDocumentDTO dto) {
        return null;
    }

    @Override
    public List<ItemDocumentDTO> getAll() {
        return null;
    }

    @Override
    public ItemDocumentDTO findById(String id) {
        return null;
    }

    @Override
    public void delete(String id) {

    }


    private ItemDocumentDTO insertUnique(ItemDocumentDTO dto) throws FileCreateException {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        ItemDocumentEntity entity = modelMapper.map(dto, ItemDocumentEntity.class);
        entity.setId(UUID.randomUUID().toString());

        entity = repository.save(entity);

        dto = modelMapper.map(entity, ItemDocumentDTO.class);

        return dto;
    }

    private ItemDocumentDTO insertCommon(ItemDocumentDTO dto){

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        ItemDocumentEntity entity = null;
        ItemMappingEntity itemMappingEntity = modelMapper.map(dto.getItemMappings().get(0), ItemMappingEntity.class);

        entity = repository.findFirstByNameAndDocumentation_Id(dto.getName(), dto.getDocumentation().getId());
        if(entity == null){
            dto = insertUnique(dto);
        }
        else{
            entity.getItemMappings().add(itemMappingEntity);
            entity = repository.save(entity);
            dto = modelMapper.map(entity, ItemDocumentDTO.class);
        }

        return dto;
    }





}
