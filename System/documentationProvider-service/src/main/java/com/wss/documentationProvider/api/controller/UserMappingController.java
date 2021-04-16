package com.wss.documentationProvider.api.controller;

import com.wss.common.controller.IEntityController;
import com.wss.common.exception.ResourceNotFoundException;
import com.wss.common.util.Util;
import com.wss.documentationCommon.dto.DocumentationProviderDTO;
import com.wss.documentationCommon.dto.UserMappingDTO;
import com.wss.documentationCommon.dto.UserMappingDTOWP;
import com.wss.documentationCommon.request.UserMappingCreateRequestModel;
import com.wss.documentationCommon.request.UserMappingUpdateRequestModel;
import com.wss.documentationProvider.api.service.IUserMappingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/userMapping")
public class UserMappingController implements IUserMappingController{

    private final IUserMappingService service;

    public UserMappingController(@Qualifier("userMappingServiceImpl") IUserMappingService service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<UserMappingDTOWP> insert(@Valid UserMappingCreateRequestModel request) {
        UserMappingDTOWP userMappingDTO = insertProcess(request);


        return  ResponseEntity.status(HttpStatus.OK).body(userMappingDTO);
    }

    @Override
    public ResponseEntity<UserMappingDTO> initialize(@Valid UserMappingCreateRequestModel request) {
        if(service.findByUserId(request.getUserId()) != null) return ResponseEntity.ok().build();

        UserMappingDTO userMappingDTO = insertProcess(request);
        return  ResponseEntity.status(HttpStatus.OK).body(userMappingDTO);

    }

    @Override
    public ResponseEntity<UserMappingDTOWP> updateByUserId(@Valid UserMappingUpdateRequestModel request) {
        UserMappingDTOWP dto = service.findByUserId(request.getId());
        if(dto == null) throw new ResourceNotFoundException();

        DocumentationProviderDTO documentationProviderDTO = new DocumentationProviderDTO();
        documentationProviderDTO.setId(request.getProviderId());

        dto.setProvider(documentationProviderDTO);
        dto = service.update(dto);

        return ResponseEntity.ok(dto);
    }

    @Override
    public ResponseEntity<UserMappingDTOWP> update(@Valid UserMappingUpdateRequestModel request) throws ResourceNotFoundException {
        UserMappingDTOWP dto = service.findById(request.getId());
        if(dto == null) throw new ResourceNotFoundException();

        DocumentationProviderDTO documentationProviderDTO = new DocumentationProviderDTO();
        documentationProviderDTO.setId(request.getProviderId());

        dto.setProvider(documentationProviderDTO);


        dto = service.update(dto);

        return ResponseEntity.ok(dto);
    }




    @Override
    public ResponseEntity<UserMappingDTOWP> getById(String id) throws ResourceNotFoundException {
        UserMappingDTOWP dto = service.findById(id);
        if(dto == null) throw  new ResourceNotFoundException();
        return ResponseEntity.ok(dto);
    }

    @Override
    public ResponseEntity<UserMappingDTOWP> getByUserId(String id) throws ResourceNotFoundException{
        UserMappingDTOWP dto = service.findByUserId(id);
        if(dto == null) throw  new ResourceNotFoundException();
        return ResponseEntity.ok(dto);

    }




    @Override
    public ResponseEntity<List<UserMappingDTOWP>> getAll() {
        List<UserMappingDTOWP> userMappingDTOS = service.getAll();

        return ResponseEntity.ok(userMappingDTOS);
    }

    @Override
    public ResponseEntity<Void> delete(String id) throws ResourceNotFoundException {
        return null;
    }

    private UserMappingDTOWP insertProcess(UserMappingCreateRequestModel request){
        UserMappingDTOWP userMappingDTO = new UserMappingDTOWP();
        userMappingDTO.setUserId(request.getUserId());

        userMappingDTO = service.insert(userMappingDTO);
        return userMappingDTO;

    }


}
