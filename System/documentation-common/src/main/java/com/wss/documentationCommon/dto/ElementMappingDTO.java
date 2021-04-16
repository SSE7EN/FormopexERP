package com.wss.documentationCommon.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.wss.documentationCommon.entity.EDocumentationStatus;


import java.util.ArrayList;
import java.util.List;

public class ElementMappingDTO {

    /**
     * Primary key
     */
    private String id;

    /**
     * Item mapping
     */
    private String elementId;

    private String projectId;

    private String name;


    @JsonManagedReference(value = "elementMappingDTO-elementDocumentDTO")
    private List<ElementDocumentDTO> elementDocuments = new ArrayList<>();

    @JsonManagedReference(value = "elementMappingDTO-itemMappingDTO")
    private List<ItemMappingDTO> itemMappings;

    private EDocumentationStatus status = EDocumentationStatus.INPROGRESS;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getElementId() {
        return elementId;
    }

    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    public List<ElementDocumentDTO> getElementDocuments() {
        return elementDocuments;
    }

    public void setElementDocuments(List<ElementDocumentDTO> elementDocuments) {
        this.elementDocuments = elementDocuments;
    }

    public EDocumentationStatus getStatus() {
        return status;
    }

    public void setStatus(EDocumentationStatus status) {
        this.status = status;
    }

    public List<ItemMappingDTO> getItemMappings() {
        return itemMappings;
    }

    public void setItemMappings(List<ItemMappingDTO> itemMappings) {
        this.itemMappings = itemMappings;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
