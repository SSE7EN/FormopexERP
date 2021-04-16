package com.wss.projectElementCommon.dto;

/**
 * DTO for element type
 *
 * @author se7en
 *
 */
public class ElementTypeDTO {

    /**
     * Primary key
     */
    public String id;

    /**
     * Element type name
     */
    public String name;

    /**
     * Element type description
     */
    public String description;



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
