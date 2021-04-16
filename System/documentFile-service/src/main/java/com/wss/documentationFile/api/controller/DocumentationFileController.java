package com.wss.documentationFile.api.controller;

import com.wss.common.exception.ResourceNotFoundException;
import com.wss.common.storage.exception.StorageFileNotFoundException;

import com.wss.common.storage.service.FileSystemStorageService;
import com.wss.common.storage.service.IStorageService;
import com.wss.documentationCommon.dto.DocumentDTO;
import com.wss.documentationCommon.dto.DocumentFileDTO;
import com.wss.documentationCommon.dto.ElementMappingDTO;
import com.wss.documentationFile.api.request.UploadDocumentFileRequest;
import com.wss.documentationFile.api.service.IElementMappingService;
import com.wss.documentationFile.api.service.IMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/file")
public class DocumentationFileController {

    private final IStorageService storageService;
    private final IMainService mainService;
    private final IElementMappingService elementMappingService;


    public DocumentationFileController(IStorageService storageService, @Qualifier("mainServiceImpl") IMainService mainService, @Qualifier("elementMappingServiceImpl") IElementMappingService elementMappingService){
        this.storageService = storageService;
        this.mainService = mainService;
        this.elementMappingService = elementMappingService;
    }

    @GetMapping("/")
    public String listUploadedFiles(Model model) throws IOException {

        model.addAttribute("files", storageService.loadAll().map(
                path -> MvcUriComponentsBuilder.fromMethodName(DocumentationFileController.class,
                        "serveFile", path.getFileName().toString()).build().toUri().toString())
                .collect(Collectors.toList()));

        return "uploadForm";
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @GetMapping("/documentations")
    public ResponseEntity<Resource> serveFileTest(@RequestParam List<String> elementsMappingsIds) {
        List<ElementMappingDTO> elementMappingDTOS = new ArrayList<>();
        for(String id : elementsMappingsIds){
            ElementMappingDTO elementMappingDTO = elementMappingService.findById(id);
            if(elementMappingDTO != null) elementMappingDTOS.add(elementMappingDTO);
        }

        try {

           Resource file = mainService.downloadElementsDocuments(elementMappingDTOS);
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                    "attachment; filename=\"" + file.getFilename() + "\"").body(file);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }


        return ResponseEntity.ok().build();
    }

    @PostMapping("/")
    public ResponseEntity<DocumentFileDTO> handleFileUpload(@RequestParam("file") MultipartFile file,
                                                            @RequestParam("documentId")  String documentId) {

        DocumentFileDTO dto = mainService.uploadFile(documentId, file);
        if(dto == null) throw new ResourceNotFoundException();

        return ResponseEntity.ok(dto);
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

}
