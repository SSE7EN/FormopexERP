package com.wss.documentationFile.api.service;

import com.wss.documentationCommon.dto.DocumentDTO;
import org.springframework.stereotype.Service;

@Service
public interface IDocumentService {


    public DocumentDTO uploadById(String id);

    public DocumentDTO findById(String id);



}
