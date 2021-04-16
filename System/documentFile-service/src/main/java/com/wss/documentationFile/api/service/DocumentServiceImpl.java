package com.wss.documentationFile.api.service;

import com.wss.documentationCommon.dto.DocumentDTO;
import com.wss.documentationCommon.dto.DocumentationDTO;
import com.wss.documentationCommon.entity.DocumentEntity;
import com.wss.documentationCommon.entity.DocumentationEntity;
import com.wss.documentationCommon.entity.EDocumentationStatus;
import com.wss.documentationFile.api.repository.IDocumentRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class DocumentServiceImpl implements IDocumentService {

    private final IDocumentRepository repository;

    public DocumentServiceImpl(IDocumentRepository repository) {
        this.repository = repository;
    }

    @Override
    public DocumentDTO uploadById(String id) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        DocumentEntity documentEntity = repository.findById(id).orElse(null);
        if(documentEntity == null) return null;

        documentEntity.setStatus(EDocumentationStatus.COMPLETED);
        documentEntity = repository.save(documentEntity);

        DocumentDTO documentDTO = modelMapper.map(documentEntity, DocumentDTO.class);

        return  documentDTO;
    }

    @Override
    public DocumentDTO findById(String id) {

        DocumentEntity entity = repository.findById(id).orElse(null);
        if(entity == null) return null;
        DocumentDTO dto = new ModelMapper().map(entity, DocumentDTO.class);

        return  dto;
    }



}
