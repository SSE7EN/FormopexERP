package com.wss.authservice.services;

import com.wss.authservice.dto.AppUserDTO;
import com.wss.common.model.EUserPermission;
import com.wss.common.model.EUserRole;
import com.wss.common.service.IEntityService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface IAppUserService extends IEntityService<AppUserDTO> {

    public boolean emailExist(String email);

    public AppUserDTO setRolesAndPermissions(AppUserDTO appUserDTO, Set<EUserRole> roles, Set<EUserPermission> permissions);
}
