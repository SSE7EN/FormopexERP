package com.wss.itemList.api.request;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * ItemList class for create request
 */
public class ItemListCreateRequestModel {

    /**
     * Name
     */
    @Length(max= 50)
    private String name;

    /**
     * Description
     */
    @Length(max = 255)
    private String description;

    /**
     * Project id
     */
    @NotNull(message = "project id cannot be null")
    public String projectId;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
