package com.wss.documentationProvider.api.service;

import com.wss.common.service.IEntityService;
import com.wss.documentationCommon.dto.UserMappingDTO;
import com.wss.documentationCommon.dto.UserMappingDTOWP;
import org.springframework.stereotype.Service;

@Service
public interface IUserMappingService extends IEntityService<UserMappingDTOWP> {

    public UserMappingDTOWP findByUserId(String id);

}
