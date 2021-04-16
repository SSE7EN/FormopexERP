package com.wss.documentationTemplate.api.request;

import javax.validation.constraints.NotNull;

public class DTemplateUpdateRequestModel extends DTemplateCreateRequestModel {
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
