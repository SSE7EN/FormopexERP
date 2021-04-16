package com.wss.documentation.api.service;

import com.wss.common.exception.FileCreateException;
import com.wss.common.util.Util;
import com.wss.documentation.api.repository.IDocumentationRepository;
import com.wss.documentationCommon.dto.DocumentationDTO;
import com.wss.documentationCommon.entity.DocumentationEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class DocumentationServiceImpl implements IDocumentationService {

    private final IDocumentationRepository repository;

    public DocumentationServiceImpl(IDocumentationRepository repository) {
        this.repository = repository;
    }

    @Override
    public DocumentationDTO insert(DocumentationDTO dto) throws FileCreateException {

        //create documentation folder
        //Util.createFile(dto.getName()+"/common");

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        DocumentationEntity entity = modelMapper.map(dto,DocumentationEntity.class);
        entity.setId(UUID.randomUUID().toString());

        entity = repository.save(entity);
        dto = modelMapper.map(entity, DocumentationDTO.class);

        return dto;
    }

    @Override
    public DocumentationDTO update(DocumentationDTO dto) {
        return null;
    }

    @Override
    public List<DocumentationDTO> getAll() {
        ArrayList<DocumentationEntity> documentations = (ArrayList<DocumentationEntity>) repository.findAll();

        ModelMapper modelMapper = new ModelMapper();
        List<DocumentationDTO> documentationDTOS = documentations
                .stream()
                .map(documentation -> modelMapper.map(documentation, DocumentationDTO.class))
                .collect(Collectors.toList());

        return documentationDTOS;



    }

    @Override
    public DocumentationDTO findById(String id) {

        DocumentationEntity entity = repository.findById(id).orElse(null);
        if(entity == null) return null;
        DocumentationDTO dto = new ModelMapper().map(entity, DocumentationDTO.class);

        return  dto;
    }

    @Override
    public void delete(String id) {

    }
}
