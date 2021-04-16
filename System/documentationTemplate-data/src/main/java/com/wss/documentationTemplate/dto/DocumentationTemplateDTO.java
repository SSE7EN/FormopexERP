package com.wss.documentationTemplate.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.ArrayList;
import java.util.List;

/**
 * Data Transfer Object for Documentation Template
 *
 * @author se7en
 */
public class DocumentationTemplateDTO {


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
     * element documentation template list
     */
    @JsonManagedReference(value = "documentationTemplate-elementDocumentation")
    private List<ElementDocumentationTemplateDTO> elementDocumentationTemplates = new ArrayList();



    /**
     *
     */
    @JsonManagedReference(value = "documentationTemplate-itemDocumentation")
    private List<ItemDocumentationTemplateDTO> itemDocumentationTemplates = new ArrayList<>();

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

    public List<ElementDocumentationTemplateDTO> getElementDocumentationTemplates() {
        return elementDocumentationTemplates;
    }

    public void setElementDocumentationTemplates(List<ElementDocumentationTemplateDTO> elementDocumentationTemplates) {
        this.elementDocumentationTemplates = elementDocumentationTemplates;
    }

    public List<ItemDocumentationTemplateDTO> getItemDocumentationTemplates() {
        return itemDocumentationTemplates;
    }

    public void setItemDocumentationTemplates(List<ItemDocumentationTemplateDTO> itemDocumentationTemplates) {
        this.itemDocumentationTemplates = itemDocumentationTemplates;
    }
}
