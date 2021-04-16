package com.wss.documentationCommon.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;

public class DocumentFileDTO {

    /**
     * Primary key
     */
    private String id;

    @JsonBackReference("documentDTO=-documentFileDTO")
    private DocumentDTO document;

    private String path;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public DocumentDTO getDocument() {
        return document;
    }

    public void setDocument(DocumentDTO document) {
        this.document = document;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
