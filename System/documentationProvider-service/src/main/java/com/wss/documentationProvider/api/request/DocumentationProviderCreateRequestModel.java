package com.wss.documentationProvider.api.request;

import javax.validation.constraints.NotNull;

public class DocumentationProviderCreateRequestModel {

    /**
     * Name
     */
    @NotNull(message = "Name cannot be null")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
