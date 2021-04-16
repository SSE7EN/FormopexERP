package com.wss.itemType.api.request;

import org.hibernate.validator.constraints.Length;

public class ItemTypeCreateRequestModel {

    /**
     * Name
     */
    @Length(max = 50)
    private String name;

    /**
     * Description
     */
    @Length(max=255)
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
