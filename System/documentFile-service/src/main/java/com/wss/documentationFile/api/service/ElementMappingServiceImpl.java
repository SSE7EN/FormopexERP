package com.wss.documentationFile.api.service;

import com.wss.documentationCommon.dto.ElementMappingDTO;
import com.wss.documentationCommon.entity.ElementMappingEntity;
import com.wss.documentationFile.api.repository.IElementMappingRepository;
import com.wss.projectElementCommon.entity.ProjectEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class ElementMappingServiceImpl implements IElementMappingService {


    private final IElementMappingRepository repository;

    public ElementMappingServiceImpl(IElementMappingRepository repository) {
        this.repository = repository;
    }

    @Override
    public ElementMappingDTO insert(ElementMappingDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        ElementMappingEntity entity = modelMapper.map(dto, ElementMappingEntity.class);
        entity.setId(UUID.randomUUID().toString());

        entity = repository.save(entity);

        dto = modelMapper.map(entity, ElementMappingDTO.class);
        return dto;
    }

    @Override
    public ElementMappingDTO update(ElementMappingDTO dto) {
        return null;
    }

    @Override
    public List<ElementMappingDTO> getAll() {
        return null;
    }

    @Override
    public ElementMappingDTO findById(String id) {
        ElementMappingEntity elementMappingEntity = repository.findById(id).orElse(null);
        if(elementMappingEntity == null) return null;
        ElementMappingDTO elementMappingDTO = new ModelMapper().map(elementMappingEntity, ElementMappingDTO.class);

        return  elementMappingDTO;

    }

    @Override
    public List<ElementMappingDTO> findByProjectId(String projectId) {


        return null;
    }

    @Override
    public void delete(String id) {

    }
}
