package com.wss.documentation.api.request;

import javax.validation.constraints.NotNull;

/**
 * This class represents a documentation type update request
 *
 * @author se7en
 */
public class DocumentationUpdateRequest extends DocumentationCreateRequest {

    /**
     * Primary Key
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
