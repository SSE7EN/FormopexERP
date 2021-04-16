package com.wss.element.api.service;

import com.wss.element.api.repository.IElementRepository;
import com.wss.projectElementCommon.dto.ElementDTO;
import com.wss.projectElementCommon.dto.ElementWithProjectDTO;
import com.wss.projectElementCommon.entity.ElementEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Element service interface implementation
 *
 * @author se7en
 */
@Service
@Transactional
public class ElementServiceImpl implements IElementService {

    @Autowired
    IElementRepository elementRepository;

    @Override
    @Transactional
    public List<ElementDTO> insert(ElementWithProjectDTO elementDTO, int count) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.getConfiguration().setSkipNullEnabled(true);

        int elementCount = elementRepository.countAllByNameAndProject_Id(elementDTO.getName(), elementDTO.getProject().getId());
        List<ElementEntity> elementEntities = new ArrayList<>();
        ElementEntity elementEntity = modelMapper.map(elementDTO, ElementEntity.class);

        for(int i = 1; i <= count; ++i){
            elementEntities.add(new ElementEntity(elementEntity, UUID.randomUUID().toString(), elementCount + i));
        }

        elementEntities = elementRepository.saveAll(elementEntities);

        List<ElementDTO> elementsDTO = elementEntities
                .stream()
                .map(elementEntityTemp -> modelMapper.map(elementEntityTemp, ElementDTO.class))
                .collect(Collectors.toList());

        return elementsDTO;
    }

    @Override
    public ElementWithProjectDTO update(ElementWithProjectDTO elementDTO) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        ElementEntity elementEntity = modelMapper.map(elementDTO, ElementEntity.class);
        elementEntity = elementRepository.save(elementEntity);

        elementDTO = modelMapper.map(elementEntity, ElementWithProjectDTO.class);

        return elementDTO;
    }

    @Override
    public List<ElementDTO> getAll() {
        ArrayList<ElementEntity> elementEntities = (ArrayList<ElementEntity>) elementRepository.findAll();

        ModelMapper modelMapper = new ModelMapper();
        List<ElementDTO> elementsDTO = elementEntities
                .stream()
                .map(elementEntity -> modelMapper.map(elementEntity, ElementDTO.class))
                .collect(Collectors.toList());

        return elementsDTO;
    }

    @Override
    public ElementDTO findById(String id) {
        ElementEntity elementEntity = elementRepository.findById(id).orElse(null);

        if(elementEntity == null) return null;

        ElementDTO elementDTO = new ModelMapper().map(elementEntity, ElementDTO.class);

        return elementDTO;
    }



    @Override
    public void delete(String id) {
        elementRepository.deleteById(id);
    }
}
