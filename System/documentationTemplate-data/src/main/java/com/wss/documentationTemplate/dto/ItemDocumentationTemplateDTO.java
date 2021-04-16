package com.wss.documentationTemplate.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.wss.documentationTemplate.entity.EItemDocumentationType;


/**
 * Data Transfer Object for Item Documentation Template
 */
public class ItemDocumentationTemplateDTO {


    /**
     * Primary key
     */
    private String id;

    /**
     * Name
     */
    private String name;

    /**
     * Description
     */
    private String description;

    /**
     * Documentation template
     */
    @JsonBackReference(value = "documentationTemplate-itemDocumentation")
    private DocumentationTemplateDTO documentationTemplate;

    /**
     * ItemTypeId
     */
    private String itemTypeId;


    private EItemDocumentationType itemDocumentationType = EItemDocumentationType.UNIQUE;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DocumentationTemplateDTO getDocumentationTemplate() {
        return documentationTemplate;
    }

    public void setDocumentationTemplate(DocumentationTemplateDTO documentationTemplate) {
        this.documentationTemplate = documentationTemplate;
    }

    public String getItemTypeId() {
        return itemTypeId;
    }

    public void setItemTypeId(String itemTypeId) {
        this.itemTypeId = itemTypeId;
    }

    public EItemDocumentationType getItemDocumentationType() {
        return itemDocumentationType;
    }

    public void setItemDocumentationType(EItemDocumentationType itemDocumentationType) {
        this.itemDocumentationType = itemDocumentationType;
    }


}
