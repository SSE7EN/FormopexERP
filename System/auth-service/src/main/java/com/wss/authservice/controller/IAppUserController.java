package com.wss.authservice.controller;

import com.wss.authservice.dto.AppUserDTO;
import com.wss.authservice.dto.AppUserPublicDTO;
import com.wss.authservice.request.AppUserCreateRequestModel;
import com.wss.authservice.request.AppUserUpdateRequestModel;
import com.wss.authservice.request.AppUserUpdateRolesAndPermissionsRequestModel;
import com.wss.common.controller.IEntityController;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface IAppUserController extends IEntityController<AppUserPublicDTO, AppUserCreateRequestModel, AppUserUpdateRequestModel> {


    @PostMapping(path = "/updateRolesAndPermissions",consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}, produces = { MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<AppUserPublicDTO> updateRolesAndPermissions(@Valid  @RequestBody AppUserUpdateRolesAndPermissionsRequestModel request);


}
