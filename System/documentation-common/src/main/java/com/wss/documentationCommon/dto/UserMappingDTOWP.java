package com.wss.documentationCommon.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.wss.documentationCommon.dto.DocumentationProviderDTO;
import com.wss.documentationCommon.dto.UserMappingDTO;

public class UserMappingDTOWP extends UserMappingDTO {
    private String providerId;

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }
}
