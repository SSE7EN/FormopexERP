package com.wss.documentationFile.api.service;

import com.wss.documentationCommon.dto.DocumentFileDTO;
import com.wss.documentationCommon.dto.ItemDocumentDTO;
import com.wss.documentationTemplate.entity.EItemDocumentationType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IDocumentFileService {

    public DocumentFileDTO insert(DocumentFileDTO dto);

    public DocumentFileDTO update(ItemDocumentDTO dto);

    public List<DocumentFileDTO> getAll();

    public DocumentFileDTO findById(String id);

    public void delete(String id);

}
