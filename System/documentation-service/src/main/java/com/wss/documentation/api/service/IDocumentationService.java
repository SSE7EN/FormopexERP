package com.wss.documentation.api.service;

import com.wss.documentationCommon.dto.DocumentationDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IDocumentationService {


    public DocumentationDTO insert(DocumentationDTO dto);

    public DocumentationDTO update(DocumentationDTO dto);

    public List<DocumentationDTO> getAll();

    public DocumentationDTO findById(String id);

    public void delete(String id);

}
