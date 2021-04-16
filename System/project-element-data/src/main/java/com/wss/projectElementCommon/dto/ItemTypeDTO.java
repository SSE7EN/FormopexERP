package com.wss.projectElementCommon.dto;

public class ItemTypeDTO {



    /**
     * Primary key
     */
    private String id;

    /**
     * Item type name
     */
    private String name;

    /**
     * Item type description
     */
    private String description;


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
}
