package com.wss.documentationCommon.dto;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.wss.documentationCommon.entity.EDocumentationStatus;


import java.util.ArrayList;
import java.util.List;

public class ItemMappingDTO {

    /**
     * Primary key
     */
    private String id;

    /**
     * Item mapping
     */
    private String itemId;

    private String name;

    @JsonManagedReference(value = "itemMappingDTO-itemDocumentDTO")
    private List<ItemDocumentDTO> itemDocuments = new ArrayList<>();

    private EDocumentationStatus status = EDocumentationStatus.INPROGRESS;

    @JsonBackReference(value = "elementMappingDTO-itemMappingDTO")
    private ElementMappingDTO elementMapping;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public List<ItemDocumentDTO> getItemDocuments() {
        return itemDocuments;
    }

    public void setItemDocuments(List<ItemDocumentDTO> itemDocuments) {
        this.itemDocuments = itemDocuments;
    }

    public EDocumentationStatus getStatus() {
        return status;
    }

    public void setStatus(EDocumentationStatus status) {
        this.status = status;
    }

    public ElementMappingDTO getElementMapping() {
        return elementMapping;
    }

    public void setElementMapping(ElementMappingDTO elementMapping) {
        this.elementMapping = elementMapping;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
