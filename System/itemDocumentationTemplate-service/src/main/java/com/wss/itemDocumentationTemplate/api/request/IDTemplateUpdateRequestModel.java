package com.wss.itemDocumentationTemplate.api.request;

import javax.validation.constraints.NotNull;

/**
 * Item documentation template request model for update
 *
 * @author se7en
 */
public class IDTemplateUpdateRequestModel extends IDTemplateCreateRequestModel{
    /**
     * Primary key
     */
    @NotNull(message = "Id cannot be null")
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


}
