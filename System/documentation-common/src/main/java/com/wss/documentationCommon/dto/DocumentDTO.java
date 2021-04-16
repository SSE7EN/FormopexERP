package com.wss.documentationCommon.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.wss.documentationCommon.entity.EDocumentationStatus;

import java.util.ArrayList;
import java.util.List;

public class DocumentDTO {
    /**
     * Primary Key
     */
    private String id;

    @JsonBackReference(value = "documentationDTO-documentDTO")
    private DocumentationDTO documentation;

    @JsonManagedReference("documentDTO-documentFileDTO")
    private List<DocumentFileDTO> documentFiles = new ArrayList<>();

    @JsonManagedReference("documentDTO-orderDTO")
    private OrderDTO order;

    private String path;

    private String name;

    private EDocumentationStatus status = EDocumentationStatus.INPROGRESS;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public DocumentationDTO getDocumentation() {
        return documentation;
    }

    public void setDocumentation(DocumentationDTO documentation) {
        this.documentation = documentation;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EDocumentationStatus getStatus() {
        return status;
    }

    public void setStatus(EDocumentationStatus status) {
        this.status = status;
    }

    public List<DocumentFileDTO> getDocumentFiles() {
        return documentFiles;
    }

    public void setDocumentFiles(List<DocumentFileDTO> documentFiles) {
        this.documentFiles = documentFiles;
    }

    public OrderDTO getOrder() {
        return order;
    }

    public void setOrder(OrderDTO order) {
        this.order = order;
    }


}
