package com.wss.elementType.api.request;

import org.hibernate.validator.constraints.Length;

/**
 * Element Type request class for creation
 *
 * @author se7en
 */
public class ElementTypeCreateRequestModel {

    /**
     * ElementType name
     */
    @Length(max = 50)
    private String name;

    /**
     * ElementType description
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
