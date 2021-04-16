package com.wss.project.request;

import javax.validation.constraints.NotNull;

public class ProjectUpdateRequestModel extends ProjectCreateRequestModel {

    /**
     * Primary key
     */

    @NotNull(message = "id cannot be null")
    private String id;

    /**
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }
}
