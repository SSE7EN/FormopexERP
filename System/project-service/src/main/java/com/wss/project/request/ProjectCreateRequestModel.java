package com.wss.project.request;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * This class represents a project insert request
 *
 * @author se7en
 */
public class ProjectCreateRequestModel {
    /**
     * Project name
     */
    @NotNull(message = "name cannot be null")
    private String name;

    /**
     * Project description
     */
    @Length(max = 255)
    private String description;

    /**
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
