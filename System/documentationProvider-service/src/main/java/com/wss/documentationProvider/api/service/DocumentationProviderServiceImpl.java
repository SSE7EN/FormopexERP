package com.wss.documentationProvider.api.service;

import com.wss.common.util.Util;
import com.wss.documentationCommon.dto.DocumentationProviderDTO;
import com.wss.documentationCommon.entity.DocumentationProviderEntity;
import com.wss.documentationProvider.api.repository.IDocumentationProviderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class DocumentationProviderServiceImpl implements IDocumentationProviderService {

    private final IDocumentationProviderRepository repository;

    public DocumentationProviderServiceImpl(IDocumentationProviderRepository repository) {
        this.repository = repository;
    }

    @Override
    public DocumentationProviderDTO insert(DocumentationProviderDTO dto) {
        ModelMapper modelMapper = Util.modelMapperStrict();

        DocumentationProviderEntity documentationProviderEntity = modelMapper.map(dto, DocumentationProviderEntity.class);
        documentationProviderEntity.setId(UUID.randomUUID().toString());

        documentationProviderEntity = repository.save(documentationProviderEntity);

        dto = modelMapper.map(documentationProviderEntity, DocumentationProviderDTO.class);

        return dto;

    }

    @Override
    public DocumentationProviderDTO update(DocumentationProviderDTO dto) {
        return null;
    }

    @Override
    public List<DocumentationProviderDTO> getAll() {
        List<DocumentationProviderEntity> documentationProviderEntities = repository.findAll();

        ModelMapper modelMapper = Util.modelMapperStrict();

        List<DocumentationProviderDTO> documentationProviderDTOS = documentationProviderEntities
                .stream()
                .map(documentationProviderEntity -> modelMapper.map(documentationProviderEntity, DocumentationProviderDTO.class))
                .collect(Collectors.toList());

        return documentationProviderDTOS;

    }

    @Override
    public DocumentationProviderDTO findById(String id) {
        DocumentationProviderEntity documentationProviderEntity = repository.findById(id).orElse(null);
        if(documentationProviderEntity == null) return null;

        ModelMapper modelMapper = Util.modelMapperStrict();
        DocumentationProviderDTO documentationProviderDTO = modelMapper.map(documentationProviderEntity, DocumentationProviderDTO.class);

        return documentationProviderDTO;
    }

    @Override
    public void delete(String id) {

    }
}
