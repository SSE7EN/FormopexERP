package com.wss.documentationFile.api.service;

import com.wss.documentationCommon.dto.ElementMappingDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IElementMappingService {

    public ElementMappingDTO insert(ElementMappingDTO dto);

    public ElementMappingDTO update(ElementMappingDTO dto);

    public List<ElementMappingDTO> getAll();

    public ElementMappingDTO findById(String id);

    public List<ElementMappingDTO> findByProjectId(String projectId);

    public void delete(String id);
}
