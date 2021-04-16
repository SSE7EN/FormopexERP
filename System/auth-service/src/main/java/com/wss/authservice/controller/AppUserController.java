package com.wss.authservice.controller;

import com.wss.authservice.dto.AppUserDTO;
import com.wss.authservice.dto.AppUserPublicDTO;
import com.wss.authservice.request.AppUserCreateRequestModel;
import com.wss.authservice.request.AppUserUpdateRequestModel;
import com.wss.authservice.request.AppUserUpdateRolesAndPermissionsRequestModel;
import com.wss.authservice.services.IAppUserService;
import com.wss.common.controller.IEntityController;
import com.wss.common.exception.RequestException;
import com.wss.common.exception.ResourceNotFoundException;
import com.wss.common.model.EUserRole;
import com.wss.common.util.Util;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/appUser")
public class AppUserController implements IAppUserController {

    private final IAppUserService service;

    public AppUserController(@Qualifier("appUserServiceImpl") IAppUserService service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<AppUserPublicDTO> insert(@Valid AppUserCreateRequestModel request) {
        if(service.emailExist(request.getEmail())) throw new RequestException(HttpStatus.CONFLICT, "Email exist");
        ModelMapper modelMapper = Util.modelMapperStrict();
        AppUserDTO appUserDTO = modelMapper.map(request, AppUserDTO.class);
        appUserDTO.setRoles(EUserRole.USER.getCode());

        appUserDTO = service.insert(appUserDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(appUserDTO);
    }

    @Override
    public ResponseEntity<AppUserPublicDTO> updateRolesAndPermissions(@Valid AppUserUpdateRolesAndPermissionsRequestModel request) {
        AppUserDTO appUserDTO = service.findById(request.getId());

        if(appUserDTO == null) throw new ResourceNotFoundException();

        appUserDTO = service.setRolesAndPermissions(appUserDTO, request.getRoles(), request.getPermissions());

        return ResponseEntity.ok(appUserDTO);
    }

    @Override
    public ResponseEntity<AppUserPublicDTO> update(@Valid AppUserUpdateRequestModel request) throws ResourceNotFoundException {
        return null;
    }

    @Override
    public ResponseEntity<AppUserPublicDTO> getById(String id) throws ResourceNotFoundException {
        AppUserPublicDTO appUserPublicDTO = service.findById(id);
        if(appUserPublicDTO == null) throw new ResourceNotFoundException();
        return ResponseEntity.ok(appUserPublicDTO);
    }

    @Override
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<List<AppUserPublicDTO>> getAll() {
        List<AppUserDTO> userDTOS = service.getAll();
        List<AppUserPublicDTO> appUserPublicDTOS = new ArrayList<>();
        for (AppUserDTO appUserDTO: userDTOS) {
            appUserPublicDTOS.add(appUserDTO);
        }



        return ResponseEntity.ok(appUserPublicDTOS);
    }

    @Override
    public ResponseEntity<Void> delete(String id) throws ResourceNotFoundException {
        return null;
    }


}
