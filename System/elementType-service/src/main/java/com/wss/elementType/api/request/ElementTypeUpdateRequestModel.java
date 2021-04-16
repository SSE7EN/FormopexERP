package com.wss.elementType.api.request;

import javax.validation.constraints.NotNull;

/**
 * ElementType request class for update
 *
 * @author se7en
 */
public class ElementTypeUpdateRequestModel extends ElementTypeCreateRequestModel {

    /**
     * Primary Key
     */
    @NotNull(message = "id cannot be null")
    private String id;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
