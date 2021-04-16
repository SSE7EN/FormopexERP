package com.wss.documentationFile.api.service;

import com.wss.documentationCommon.dto.DocumentFileDTO;
import com.wss.documentationCommon.dto.ItemDocumentDTO;
import com.wss.documentationCommon.entity.DocumentFileEntity;
import com.wss.documentationFile.api.repository.IDocumentationFileRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class DocumentFileServiceImpl implements IDocumentFileService  {

    private final IDocumentationFileRepository repository;

    public DocumentFileServiceImpl(IDocumentationFileRepository repository) {
        this.repository = repository;
    }

    @Override
    public DocumentFileDTO insert(DocumentFileDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        DocumentFileEntity documentFileEntity = modelMapper.map(dto, DocumentFileEntity.class);
        documentFileEntity.setId(UUID.randomUUID().toString());

        documentFileEntity = repository.save(documentFileEntity);

        dto = modelMapper.map(documentFileEntity, DocumentFileDTO.class);
        return dto;


    }

    @Override
    public DocumentFileDTO update(ItemDocumentDTO dto) {
        return null;
    }

    @Override
    public List<DocumentFileDTO> getAll() {
        return null;
    }

    @Override
    public DocumentFileDTO findById(String id) {
        return null;
    }

    @Override
    public void delete(String id) {

    }
}
