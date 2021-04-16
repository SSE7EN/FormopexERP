package com.wss.elementDocumentationTemplate.api.request;

import com.wss.documentationTemplate.dto.DocumentationTemplateDTO;

import javax.validation.constraints.NotNull;

public class EDTemplateCreateRequestModel {


    /**
     * Item Documentation Template id
     */
    @NotNull(message = "Documentation Template id cannot be null")
    private String dTemplateId;

    /**
     * Element type id
     */
    @NotNull(message = "Element type id cannot be null")
    private String elementTypeId;

    /**
     * Name
     */
    private String name;

    /**
     * Description
     */
    private String description;

    public String getdTemplateId() {
        return dTemplateId;
    }

    public void setdTemplateId(String dTemplateId) {
        this.dTemplateId = dTemplateId;
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
