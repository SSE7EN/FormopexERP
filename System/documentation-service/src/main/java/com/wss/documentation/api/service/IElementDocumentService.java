package com.wss.documentation.api.service;

import com.wss.documentationCommon.dto.ElementDocumentDTO;
import com.wss.projectElementCommon.dto.ElementDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IElementDocumentService {


    public ElementDocumentDTO insert(ElementDocumentDTO dto);

    public ElementDocumentDTO update(ElementDocumentDTO dto);

    public List<ElementDocumentDTO> getAll();

    public ElementDocumentDTO findById(String id);

    public void delete(String id);

}
