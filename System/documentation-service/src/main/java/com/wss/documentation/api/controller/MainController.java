package com.wss.documentation.api.controller;

import com.wss.common.security.JwtProperties;
import com.wss.documentation.api.request.DocumentationCreateRequest;
import com.wss.documentation.api.service.IMainService;
import com.wss.documentationCommon.dto.DocumentationDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/main")
public class MainController {

    private final IMainService mainService;

    public MainController(@Qualifier("mainServiceImpl") IMainService mainService) {
        this.mainService = mainService;
    }

    @PostMapping( path = "/generate", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<DocumentationDTO> generate(@Valid @RequestBody DocumentationCreateRequest request, HttpServletRequest httpRequest){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        DocumentationDTO dto = modelMapper.map(request, DocumentationDTO.class);
        dto = mainService.generate(dto, httpRequest.getHeader(JwtProperties.HEADER_STRING));

        return ResponseEntity.ok().body(dto);
    }
}
