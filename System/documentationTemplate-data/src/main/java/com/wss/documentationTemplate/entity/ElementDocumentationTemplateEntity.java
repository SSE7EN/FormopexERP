package com.wss.documentationTemplate.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Element documentation template entity
 *
 * @author se7en
 */
@Entity
@Table(name = "element_documentation_template",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"documentation_template_id", "name"})})
public class ElementDocumentationTemplateEntity implements Serializable {

    private static long serialVersionUID = 1L;

    /**
     * Primary key
     */
    @Id
    @Column(name = "id", unique = true, nullable = false)
    private String id;

    /**
     * Documentation template
     */
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "documentation_template_id")
    private DocumentationTemplateEntity documentationTemplate;

    /**
     * Element type id
     */
    @Column(name = "element_type_id", nullable = false)
    private String elementTypeId;

    /**
     * Name
     */
    @Column(name = "name", length = 55)
    private String name;

    /**
     * Description
     */
    @Column(name = "description")
    private String description;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public static void setSerialVersionUID(long serialVersionUID) {
        ElementDocumentationTemplateEntity.serialVersionUID = serialVersionUID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public DocumentationTemplateEntity getDocumentationTemplate() {
        return documentationTemplate;
    }

    public void setDocumentationTemplate(DocumentationTemplateEntity documentationTemplate) {
        this.documentationTemplate = documentationTemplate;
    }

    public String getElementTypeId() {
        return elementTypeId;
    }

    public void setElementTypeId(String elementTypeId) {
        this.elementTypeId = elementTypeId;
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
