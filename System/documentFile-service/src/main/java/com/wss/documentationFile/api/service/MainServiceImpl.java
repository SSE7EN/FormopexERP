package com.wss.documentationFile.api.service;

import com.wss.common.exception.ResourceNotFoundException;
import com.wss.common.storage.exception.StorageException;
import com.wss.common.storage.service.IStorageService;
import com.wss.common.util.ZipUtil;
import com.wss.documentationCommon.dto.*;
import com.wss.documentationFile.api.request.UploadDocumentFileRequest;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
public class MainServiceImpl implements IMainService {

    private final IDocumentService documentService;
    private final IStorageService storageService;
    private final IDocumentFileService documentFileService;

    public MainServiceImpl(@Qualifier("documentServiceImpl") IDocumentService documentService, IStorageService storageService,
                           @Qualifier("documentFileServiceImpl") IDocumentFileService documentFileService) {
        this.documentService = documentService;
        this.storageService = storageService;
        this.documentFileService = documentFileService;
    }

    @Override
    public DocumentFileDTO uploadFile(String id, MultipartFile file) throws StorageException{
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        DocumentDTO documentDTO = documentService.findById(id);
        if(documentDTO == null) return null;

        DocumentFileDTO documentFileDTO = createDocumentFileDTO(documentDTO,file);
        documentFileService.insert(documentFileDTO);

        storageService.store(file, documentDTO.getPath());
        documentService.uploadById(documentDTO.getId());

        return documentFileDTO;

    }

    @Override
    public Resource downloadElementsDocuments(List<ElementMappingDTO> elementMappingDTOS) throws IOException {
        List<DocumentDTO> documentDTOS = new ArrayList<>();

        //add storage location to zip file
        FileOutputStream fos = new FileOutputStream(storageService.getPath("documentation.zip"));
        ZipOutputStream zipOut = new ZipOutputStream(fos);

        Set<String> documentPaths = new HashSet<>();

        for(ElementMappingDTO elementMDTO : elementMappingDTOS) {
            for (DocumentDTO elementDDTO : elementMDTO.getElementDocuments()) {
                for (DocumentFileDTO documentFileDTO : elementDDTO.getDocumentFiles()) {
                    documentPaths.add(documentFileDTO.getPath());
                }

            }
            for (ItemMappingDTO itemMappingDTO : elementMDTO.getItemMappings()) {
                for (DocumentDTO documentDTO : itemMappingDTO.getItemDocuments()) {
                    for (DocumentFileDTO documentFileDTO : documentDTO.getDocumentFiles()) {
                        documentPaths.add(documentFileDTO.getPath());
                    }
                }
            }
        }
        for(String path : documentPaths){
            ZipUtil.zipTest(path, storageService.getLocation(), zipOut);
        }
        zipOut.close();
        fos.close();



        return storageService.loadAsResource("documentation.zip");
    }

    private DocumentFileDTO createDocumentFileDTO(DocumentDTO documentDTO, MultipartFile file){
        DocumentFileDTO documentFileDTO = new DocumentFileDTO();
        documentFileDTO.setDocument(documentDTO);
        documentFileDTO.setPath(documentDTO.getPath()+"/"+file.getOriginalFilename());

        return documentFileDTO;
    }
}
