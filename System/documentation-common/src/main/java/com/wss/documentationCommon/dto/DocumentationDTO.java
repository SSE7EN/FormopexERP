package com.wss.documentationCommon.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.ArrayList;
import java.util.List;

/**
 * DTO for DocumentationType
 *
 * @author se7en
 */
public class DocumentationDTO {

    /**
     * Primary key
     */
    private String id;

    /**
     * Documentation type name
     */
    private String name;

    /**
     * Documentation type description
     */
    private String description;


    /**
     * documentation template id
     */
    private String documentationTemplateId;

    /**
     * Project id
     */
    private String projectId;


    @JsonManagedReference(value = "documentationDTO-documentDTO")
    private List<DocumentDTO> documents = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getDocumentationTemplateId() {
        return documentationTemplateId;
    }

    public void setDocumentationTemplateId(String documentationTemplateId) {
        this.documentationTemplateId = documentationTemplateId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public List<DocumentDTO> getDocuments() {
        return documents;
    }

    public void setDocuments(List<DocumentDTO> documents) {
        this.documents = documents;
    }
}
