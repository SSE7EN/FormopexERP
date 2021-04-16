package com.wss.itemDocumentationTemplate.api.service;

import com.wss.documentationTemplate.dto.ItemDocumentationTemplateDTO;
import com.wss.documentationTemplate.entity.EItemDocumentationType;
import com.wss.documentationTemplate.entity.ItemDocumentationTemplateEntity;
import com.wss.itemDocumentationTemplate.api.repository.IIDTemplateRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Transactional
@Service
public class IDTemplateServiceImpl implements IIDTemplateService {

    private final IIDTemplateRepository repository;

    public IDTemplateServiceImpl(IIDTemplateRepository repository) {
        this.repository = repository;
    }

    @Override
    public ItemDocumentationTemplateDTO insert(ItemDocumentationTemplateDTO iDTemplateDTO) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        ItemDocumentationTemplateEntity itemDocumentationTemplateEntity = modelMapper.map(iDTemplateDTO,ItemDocumentationTemplateEntity.class);
        itemDocumentationTemplateEntity.setId(UUID.randomUUID().toString());

        itemDocumentationTemplateEntity = repository.save(itemDocumentationTemplateEntity);
        iDTemplateDTO = modelMapper.map(itemDocumentationTemplateEntity, ItemDocumentationTemplateDTO.class);

        return iDTemplateDTO;
    }

    @Override
    public ItemDocumentationTemplateDTO update(ItemDocumentationTemplateDTO iDTemplateDTO) {

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        ItemDocumentationTemplateEntity itemDocumentationTemplateEntity = modelMapper.map(iDTemplateDTO,ItemDocumentationTemplateEntity.class);
        itemDocumentationTemplateEntity = repository.save(itemDocumentationTemplateEntity);
        iDTemplateDTO = modelMapper.map(itemDocumentationTemplateEntity, ItemDocumentationTemplateDTO.class);

        return iDTemplateDTO;
    }

    @Override
    public List<ItemDocumentationTemplateDTO> getAll() {
        ArrayList<ItemDocumentationTemplateEntity> itemDocumentationTemplateEntities = (ArrayList<ItemDocumentationTemplateEntity>) repository.findAll();

        ModelMapper modelMapper = new ModelMapper();
        List<ItemDocumentationTemplateDTO>  itemDocumentationTemplateDTOS = itemDocumentationTemplateEntities
                .stream()
                .map(itemDocumentationTemplateEntity -> modelMapper.map(itemDocumentationTemplateEntity, ItemDocumentationTemplateDTO.class))
                .collect(Collectors.toList());

        return itemDocumentationTemplateDTOS;
    }

    @Override
    public ItemDocumentationTemplateDTO findById(String id) {
        ItemDocumentationTemplateEntity entities = repository.findById(id).orElse(null);
        if(entities == null) return null;
        ItemDocumentationTemplateDTO dTO = new ModelMapper().map(entities, ItemDocumentationTemplateDTO.class);

        return  dTO;
    }

    @Override
    public List<EItemDocumentationType> getAllItemDocumentationTypes() {
        List<EItemDocumentationType> eItemDocumentationTypes = Arrays.asList(EItemDocumentationType.class.getEnumConstants());

        return eItemDocumentationTypes;
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }
}
