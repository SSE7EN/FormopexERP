package com.wss.elementDocumentationTemplate.api.service;

import com.wss.documentationTemplate.dto.ElementDocumentationTemplateDTO;
import com.wss.documentationTemplate.dto.ItemDocumentationTemplateDTO;
import com.wss.documentationTemplate.entity.ElementDocumentationTemplateEntity;
import com.wss.elementDocumentationTemplate.api.repository.IEDTemplateRepository;
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
public class EDTemplateServiceImpl implements IEDTemplateService {

    private final IEDTemplateRepository repository;

    public EDTemplateServiceImpl(IEDTemplateRepository repository) {
        this.repository = repository;
    }

    @Override
    public ElementDocumentationTemplateDTO insert(ElementDocumentationTemplateDTO eDTemplateDTO) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        ElementDocumentationTemplateEntity entity = modelMapper.map(eDTemplateDTO,ElementDocumentationTemplateEntity.class);
        entity.setId(UUID.randomUUID().toString());

        entity = repository.save(entity);
        eDTemplateDTO = modelMapper.map(entity, ElementDocumentationTemplateDTO.class);

        return eDTemplateDTO;
    }

    @Override
    public ElementDocumentationTemplateDTO update(ElementDocumentationTemplateDTO eDTemplateDTO) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        ElementDocumentationTemplateEntity entity = modelMapper.map(eDTemplateDTO,ElementDocumentationTemplateEntity.class);
        entity = repository.save(entity);
        eDTemplateDTO = modelMapper.map(entity, ElementDocumentationTemplateDTO.class);

        return eDTemplateDTO;

    }

    @Override
    public List<ElementDocumentationTemplateDTO> getAll() {
        ArrayList<ElementDocumentationTemplateEntity> entities = (ArrayList<ElementDocumentationTemplateEntity>) repository.findAll();

        ModelMapper modelMapper = new ModelMapper();
        List<ElementDocumentationTemplateDTO>  dTOs = entities
                .stream()
                .map(entity -> modelMapper.map(entity, ElementDocumentationTemplateDTO.class))
                .collect(Collectors.toList());

        return dTOs;
    }

    @Override
    public ElementDocumentationTemplateDTO findById(String id) {
        ElementDocumentationTemplateEntity entity = repository.findById(id).orElse(null);
        if(entity == null) return null;
        ElementDocumentationTemplateDTO dTO = new ModelMapper().map(entity, ElementDocumentationTemplateDTO.class);

        return  dTO;
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }
}
