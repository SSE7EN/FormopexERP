package com.wss.documentationCommon.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Documentation type Entity
 *
 * @author se7en
 */
@Entity
@Table(name="documentation")
public class DocumentationEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Primary key
     */
    @Id
    @Column(name="id", nullable = false, unique = true)
    private String id;

    /**
     * Documentation type name
     */
    @Column(name = "name", length = 50, unique = true)
    private String name;

    /**
     * Documentation type description
     */
    @Column(name="description", length = 255)
    private String description;

    /**
     * Documentation Template id
     */
    @Column(name="documentation_template_id", nullable = false)
    private String documentationTemplateId;

    /**
     *  Project id
     */
    @Column(name = "project_id", unique = true, nullable = false)
    private String projectId;

    /**
     * Documents
     */
    @OneToMany(mappedBy = "documentation", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<DocumentEntity> documents = new ArrayList<>();


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

    public List<DocumentEntity> getDocuments() {
        return documents;
    }

    public void setDocuments(List<DocumentEntity> documents) {
        this.documents = documents;
    }

    /**
     *
     * @return
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     *
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
