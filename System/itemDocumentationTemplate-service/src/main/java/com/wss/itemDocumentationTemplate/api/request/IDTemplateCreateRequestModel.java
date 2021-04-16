package com.wss.itemDocumentationTemplate.api.request;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * Item documentation template request model
 *
 * @author se7en
 */
public class IDTemplateCreateRequestModel {

    /**
     *  Name
     */
    @Length(max= 55)
    private String name;

    /**
     * Description
     */
    @Length(max = 255)
    private String description;

    /**
     * Item Documentation Template id
     */
    @NotNull(message = "Item Documentation Template id cannot be null")
    private String dTemplateId;

    /**
     * Item Documentation type
     */
    private String itemDocumentationType;

    /**
     * Item Type id
     */
    private String itemTypeId;

    public String getItemTypeId() {
        return itemTypeId;
    }

    public void setItemTypeId(String itemTypeId) {
        this.itemTypeId = itemTypeId;
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

    public String getdTemplateId() {
        return dTemplateId;
    }

    public void setdTemplateId(String dTemplateId) {
        this.dTemplateId = dTemplateId;
    }

    public String getItemDocumentationType() {
        return itemDocumentationType;
    }

    public void setItemDocumentationType(String itemDocumentationType) {
        this.itemDocumentationType = itemDocumentationType;
    }
}
