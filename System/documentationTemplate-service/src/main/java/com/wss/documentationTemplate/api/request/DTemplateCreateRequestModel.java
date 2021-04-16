package com.wss.documentationTemplate.api.request;

public class DTemplateCreateRequestModel {


    /**
     * Name
     */
    private String name;

    /**
     * Description
     */
    private String description;


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
