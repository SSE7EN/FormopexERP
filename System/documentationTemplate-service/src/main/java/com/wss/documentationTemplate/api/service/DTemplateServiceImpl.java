package com.wss.documentationTemplate.api.service;

import com.wss.documentationTemplate.api.repository.IDTemplateRepository;
import com.wss.documentationTemplate.dto.DocumentationTemplateDTO;
import com.wss.documentationTemplate.entity.DocumentationTemplateEntity;
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
public class DTemplateServiceImpl implements IDTemplateService {

    private final IDTemplateRepository repository;

    public DTemplateServiceImpl(IDTemplateRepository repository) {
        this.repository = repository;
    }

    @Override
    public DocumentationTemplateDTO insert(DocumentationTemplateDTO dTemplateDTO) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        DocumentationTemplateEntity entity = modelMapper.map(dTemplateDTO,DocumentationTemplateEntity.class);
        entity.setId(UUID.randomUUID().toString());

        entity = repository.save(entity);
        dTemplateDTO = modelMapper.map(entity, DocumentationTemplateDTO.class);

        return dTemplateDTO;
    }

    @Override
    public DocumentationTemplateDTO update(DocumentationTemplateDTO dTemplateDTO) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        DocumentationTemplateEntity entity = modelMapper.map(dTemplateDTO,DocumentationTemplateEntity.class);
        entity = repository.save(entity);
        dTemplateDTO = modelMapper.map(entity, DocumentationTemplateDTO.class);

        return dTemplateDTO;
    }

    @Override
    public List<DocumentationTemplateDTO> getAll() {
        ArrayList<DocumentationTemplateEntity> entities = (ArrayList<DocumentationTemplateEntity>) repository.findAll();

        ModelMapper modelMapper = new ModelMapper();
        List<DocumentationTemplateDTO>  dTOs = entities
                .stream()
                .map(entity -> modelMapper.map(entity, DocumentationTemplateDTO.class))
                .collect(Collectors.toList());

        return dTOs;
    }

    @Override
    public DocumentationTemplateDTO findById(String id) {
        DocumentationTemplateEntity entity = repository.findById(id).orElse(null);
        if(entity == null) return null;
        DocumentationTemplateDTO dTO = new ModelMapper().map(entity, DocumentationTemplateDTO.class);

        return  dTO;
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }
}
