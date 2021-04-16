package com.wss.element.api.request;

import javax.validation.constraints.NotNull;

/**
 * Element request class for update
 */
public class ElementUpdateRequestModel extends ElementCreateRequestModel {

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
