package com.wss.documentationCommon.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;

public class UserMappingDTO {

    /**
     * Primary key
     */
    private String id;

    /**
     * User id
     */
    private String userId;

    /**
     * Provider
     */
    @JsonBackReference("providerDTO-userMappingDTO")
    private DocumentationProviderDTO provider;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public DocumentationProviderDTO getProvider() {
        return provider;
    }

    public void setProvider(DocumentationProviderDTO provider) {
        this.provider = provider;
    }
}
