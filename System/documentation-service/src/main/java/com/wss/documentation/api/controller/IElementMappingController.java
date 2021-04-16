package com.wss.documentation.api.controller;

import com.wss.common.controller.IEntityController;
import com.wss.common.exception.ResourceNotFoundException;
import com.wss.documentation.api.request.ElementMappingCreateRequestModel;
import com.wss.documentation.api.request.ElementMappingUpdateRequestModel;
import com.wss.documentationCommon.dto.ElementMappingDTO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface IElementMappingController extends IEntityController<ElementMappingDTO, ElementMappingCreateRequestModel, ElementMappingUpdateRequestModel> {

    @GetMapping(value = "/project/{projectId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<ElementMappingDTO>> getAllByProjectId(@PathVariable String projectId);
}
