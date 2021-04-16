package com.wss.documentationFile.api.service;

import com.wss.documentationCommon.dto.DocumentDTO;
import com.wss.documentationCommon.dto.DocumentFileDTO;
import com.wss.documentationCommon.dto.ElementMappingDTO;
import com.wss.documentationFile.api.request.UploadDocumentFileRequest;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service

public interface IMainService {

    public DocumentFileDTO uploadFile(String id, MultipartFile file);

    public Resource downloadElementsDocuments(List<ElementMappingDTO> elementMappingDTOS) throws IOException;



}
