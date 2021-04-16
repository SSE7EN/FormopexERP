package com.wss.documentationProvider.api.request;

public class OrderUpdateRequestModel extends OrderCreateRequestModel {
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
