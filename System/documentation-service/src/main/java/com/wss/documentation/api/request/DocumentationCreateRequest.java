package com.wss.documentation.api.request;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * This class represents a documentation type create request
 *
 * @author se7en
 */
public class DocumentationCreateRequest {
    /**
     * Documentation name
     */
    @Length(max = 55)
    @NotNull(message = "Name cannot be null")
    private String name;

    /**
     * Documentation type description
     */
    @Length(max = 255)
    private String description;

    /**
     * Project id
     */
    @NotNull(message = "Project id cannot be null")
    private String projectId;

    /**
     * Documentation Template id
     */
    @NotNull(message = "documentation template cannot be null")
    private String documentationTemplateId;


    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getDocumentationTemplateId() {
        return documentationTemplateId;
    }

    public void setDocumentationTemplateId(String documentationTemplateId) {
        this.documentationTemplateId = documentationTemplateId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
