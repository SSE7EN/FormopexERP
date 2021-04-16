package com.wss.documentationTemplate.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * Data Transfer Object for Element Documentation Template
 *
 * @author se7en
 */
public class ElementDocumentationTemplateDTO {

    /**
     * Primary key
     */
    private String id;

    /**
     * Documentation template
     */
    @JsonBackReference(value = "documentationTemplate-elementDocumentation")
    private DocumentationTemplateDTO documentationTemplate;

    /**
     * Element type id
     */
    private String elementTypeId;

    /**
     * Name
     */
    private String name;

    /**
     * Description
     */
    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public DocumentationTemplateDTO getDocumentationTemplate() {
        return documentationTemplate;
    }

    public void setDocumentationTemplate(DocumentationTemplateDTO documentationTemplate) {
        this.documentationTemplate = documentationTemplate;
    }

    public String getElementTypeId() {
        return elementTypeId;
    }

    public void setElementTypeId(String elementTypeId) {
        this.elementTypeId = elementTypeId;
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


}
