package com.wss.documentationProvider.api.service;

import com.wss.common.util.Util;
import com.wss.documentationCommon.dto.UserMappingDTO;
import com.wss.documentationCommon.dto.UserMappingDTOWP;
import com.wss.documentationCommon.entity.UserMappingEntity;
import com.wss.documentationProvider.api.repository.IUserMappingRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserMappingServiceImpl implements IUserMappingService {

    private final IUserMappingRepository repository;

    public UserMappingServiceImpl(IUserMappingRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserMappingDTOWP insert(UserMappingDTOWP dto) {
        ModelMapper modelMapper = Util.modelMapperStrict();

        UserMappingEntity userMappingEntity = modelMapper.map(dto,UserMappingEntity.class);
        userMappingEntity.setId(UUID.randomUUID().toString());

        userMappingEntity = repository.save(userMappingEntity);

        dto = modelMapper.map(userMappingEntity, UserMappingDTOWP.class);

        return dto;

    }

    @Override
    public UserMappingDTOWP update(UserMappingDTOWP dto) {
        ModelMapper modelMapper = Util.modelMapperStrict();
        UserMappingEntity userMappingEntity = modelMapper.map(dto, UserMappingEntity.class);

        userMappingEntity = repository.save(userMappingEntity);
        dto = modelMapper.map(userMappingEntity, UserMappingDTOWP.class);

        return dto;

    }

    @Override
    public List<UserMappingDTOWP> getAll() {
        List<UserMappingEntity> userMappingEntities = repository.findAll();

        ModelMapper modelMapper = Util.modelMapperStrict();

        List<UserMappingDTOWP> userMappingDTOS = userMappingEntities
                .stream()
                .map(userMappingEntity -> modelMapper.map(userMappingEntity, UserMappingDTOWP.class))
                .collect(Collectors.toList());

        return userMappingDTOS;




    }

    @Override
    public UserMappingDTOWP findById(String id) {
        UserMappingEntity userMappingEntity = repository.findById(id).orElse(null);

        if(userMappingEntity == null) return null;

        ModelMapper modelMapper = Util.modelMapperStrict();
        UserMappingDTOWP dto = modelMapper.map(userMappingEntity, UserMappingDTOWP.class);

        return dto;

    }


    @Override
    public UserMappingDTOWP findByUserId(String id) {
        UserMappingEntity userMappingEntity = repository.findByUserId(id).orElse(null);

        if(userMappingEntity == null) return null;

        ModelMapper modelMapper = Util.modelMapperStrict();
        UserMappingDTOWP dto = modelMapper.map(userMappingEntity, UserMappingDTOWP.class);

        return dto;
    }

    @Override
    public void delete(String id) {

    }

}
