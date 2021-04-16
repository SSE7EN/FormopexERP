package com.wss.itemList.api.request;

import javax.validation.constraints.NotNull;

/**
 * Item request class for update
 *
 * @author se7en
 */
public class ItemUpdateRequestModel extends ItemCreateRequestModel {

    /**
     * Primary key
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
