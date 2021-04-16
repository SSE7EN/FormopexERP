package com.wss.documentation.api.service;

import com.wss.documentationCommon.dto.DocumentationDTO;
import org.springframework.stereotype.Service;

@Service
public interface IMainService {

    public DocumentationDTO generate(DocumentationDTO dto, String token);

}
