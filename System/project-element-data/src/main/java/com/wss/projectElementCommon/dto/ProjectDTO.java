package com.wss.projectElementCommon.dto;

import com.wss.projectElementCommon.entity.ElementEntity;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Data transfer Object for Project
 *
 * @author se7en
 *
 */
public class ProjectDTO {


    /**
     *
     * Primary key
     */
    private String id;

    /**
     *
     * Project name
     */
    private String name;

    /**
     *
     * Project description
     */
    private String description;



    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */

    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
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
     * @param description
     */

    public void setDescription(String description) {
        this.description = description;
    }








}
