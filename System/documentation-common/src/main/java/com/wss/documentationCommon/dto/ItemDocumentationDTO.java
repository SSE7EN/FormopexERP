package com.wss.documentationCommon.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.ArrayList;
import java.util.List;

public class ItemDocumentationDTO {

    /**
     * Primary key
     */
    private String id;

    /**
     * Documentation
     */
    @JsonBackReference
    private DocumentationDTO documentation;

    /**
     * Item mappings
     */
    @JsonManagedReference
    private List<ItemMappingDTO> itemMappings = new ArrayList<>();

    /**
     * Item documents
     */
    @JsonManagedReference
    private List<ItemDocumentDTO> itemDocuments = new ArrayList<>();

    /**
     * Enum
     */
    private String status;

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

    public List<ItemMappingDTO> getItemMappings() {
        return itemMappings;
    }

    public void setItemMappings(List<ItemMappingDTO> itemMappings) {
        this.itemMappings = itemMappings;
    }

    public List<ItemDocumentDTO> getItemDocuments() {
        return itemDocuments;
    }

    public void setItemDocuments(List<ItemDocumentDTO> itemDocuments) {
        this.itemDocuments = itemDocuments;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
