package com.wss.documentationCommon.request;

public class UserMappingUpdateRequestModel{

    /**
     * Primary key
     */
    private String id;

    private String providerId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }
}
