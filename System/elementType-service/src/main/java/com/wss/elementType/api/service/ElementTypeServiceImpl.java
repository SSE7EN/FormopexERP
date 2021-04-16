package com.wss.elementType.api.service;

import com.wss.elementType.api.repository.IElementTypeRepository;
import com.wss.elementType.api.request.ElementTypeCreateRequestModel;
import com.wss.elementType.api.request.ElementTypeUpdateRequestModel;
import com.wss.projectElementCommon.dto.ElementDTO;
import com.wss.projectElementCommon.dto.ElementTypeDTO;
import com.wss.projectElementCommon.entity.ElementTypeEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.annotation.ElementType;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * ElementType service interface implementation
 *
 * @author se7en
 */
@Service
public class ElementTypeServiceImpl implements IElementTypeService {


    @Autowired
    IElementTypeRepository  elementRepository;

    @Override
    public ElementTypeDTO insert(ElementTypeDTO elementDTO) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        ElementTypeEntity elementTypeEntity =  modelMapper.map(elementDTO, ElementTypeEntity.class);
        elementTypeEntity.setId(UUID.randomUUID().toString());
        elementTypeEntity = elementRepository.save(elementTypeEntity);

        elementDTO = modelMapper.map(elementTypeEntity, ElementTypeDTO.class);

        return elementDTO;

    }

    @Override
    public ElementTypeDTO update(ElementTypeDTO elementDTO) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        ElementTypeEntity elementTypeEntity = modelMapper.map(elementDTO, ElementTypeEntity.class);
        elementTypeEntity = elementRepository.save(elementTypeEntity);

        elementDTO = modelMapper.map(elementTypeEntity, ElementTypeDTO.class);

        return elementDTO;
    }

    @Override
    public List<ElementTypeDTO> getAll() {
        ArrayList<ElementTypeEntity> elementTypes = (ArrayList)elementRepository.findAll();

        ModelMapper modelMapper = new ModelMapper();
        List<ElementTypeDTO> elementTypesDTO = elementTypes
                .stream()
                .map(elementTypeEntity -> modelMapper.map(elementTypeEntity, ElementTypeDTO.class))
                .collect(Collectors.toList());

        return elementTypesDTO;


    }

    @Override
    public ElementTypeDTO findById(String id) {
        ElementTypeEntity elementTypeEntity = elementRepository.findById(id).orElse(null);

        if(elementTypeEntity == null) return null;

        ElementTypeDTO elementTypeDTO = new ModelMapper().map(elementTypeEntity, ElementTypeDTO.class);

        return elementTypeDTO;
    }

    @Override
    public void delete(String id) {
        elementRepository.deleteById(id);
    }
}
