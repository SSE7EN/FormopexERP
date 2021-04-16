package com.wss.documentationProvider.api.controller;

import com.wss.common.controller.IEntityController;
import com.wss.common.exception.ResourceNotFoundException;
import com.wss.documentationCommon.dto.UserMappingDTO;
import com.wss.documentationCommon.dto.UserMappingDTOWP;
import com.wss.documentationCommon.request.UserMappingCreateRequestModel;
import com.wss.documentationCommon.request.UserMappingUpdateRequestModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

public interface IUserMappingController extends  IEntityController<UserMappingDTOWP, UserMappingCreateRequestModel, UserMappingUpdateRequestModel>{

        /**
         * Insert
         * @param request
         * @return
         */
        @PostMapping(path = "/initialize",consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}, produces = { MediaType.APPLICATION_JSON_VALUE})
        public ResponseEntity<UserMappingDTO> initialize(@Valid @RequestBody UserMappingCreateRequestModel request);

        /**
         * Get by id
         * @param id
         * @return
         * @throws ResourceNotFoundException
         */
        @GetMapping(value = "/userId/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
        public ResponseEntity<UserMappingDTOWP> getByUserId(@PathVariable("id") String id) throws ResourceNotFoundException;

        @PutMapping(value="/userId",  produces = {MediaType.APPLICATION_JSON_VALUE})
        public ResponseEntity<UserMappingDTOWP> updateByUserId(@Valid @RequestBody UserMappingUpdateRequestModel request);

}
