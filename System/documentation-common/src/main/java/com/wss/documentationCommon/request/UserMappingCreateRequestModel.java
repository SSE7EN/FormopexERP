package com.wss.documentationCommon.request;

import javax.validation.constraints.NotNull;

public class UserMappingCreateRequestModel {
    /**
     * User id
     */
    @NotNull(message = "User id cannot be null")
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
