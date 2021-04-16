package com.wss.itemType.api.request;


public class ItemTypeUpdateRequestModel extends ItemTypeCreateRequestModel {
    /**
     * Primary key
     */
    private String id;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
