package com.wss.documentation.api.service;

import com.wss.common.exception.FileCreateException;
import com.wss.common.storage.StorageProperties;
import com.wss.common.storage.service.IStorageService;
import com.wss.common.util.Util;
import com.wss.documentation.api.repository.IElementDocumentRepository;
import com.wss.documentationCommon.dto.ElementDocumentDTO;
import com.wss.documentationCommon.entity.ElementDocumentEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class ElementDocumentServiceImpl implements IElementDocumentService {

    private final IElementDocumentRepository repository;
    private final StorageProperties storageProperties;
    private final IStorageService storageService;

    public ElementDocumentServiceImpl(IElementDocumentRepository repository, StorageProperties storageProperties, IStorageService storageService) {
        this.repository = repository;
        this.storageProperties = storageProperties;
        this.storageService = storageService;
    }

    @Override
    public ElementDocumentDTO insert(ElementDocumentDTO dto) throws FileCreateException {

        String path = "/"+dto.getDocumentation().getName()+"/"+dto.getElementMappings().get(0).getName()+"/"+dto.getName();
        storageService.createFile(path);

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        ElementDocumentEntity entity = modelMapper.map(dto, ElementDocumentEntity.class);
        entity.setId(UUID.randomUUID().toString());
        entity.setPath(path);

        entity = repository.save(entity);

        dto = modelMapper.map(entity, ElementDocumentDTO.class);

        return dto;
    }

    @Override
    public ElementDocumentDTO update(ElementDocumentDTO dto) {
        return null;
    }

    @Override
    public List<ElementDocumentDTO> getAll() {
        return null;
    }

    @Override
    public ElementDocumentDTO findById(String id) {
        return null;
    }

    @Override
    public void delete(String id) {

    }
}
