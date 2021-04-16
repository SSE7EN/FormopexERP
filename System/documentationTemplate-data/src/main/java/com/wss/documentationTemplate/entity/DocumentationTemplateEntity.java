package com.wss.documentationTemplate.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Documentation template entity
 *
 * @author se7en
 */

@Entity
@Table(name="documentation_template")
public class DocumentationTemplateEntity {

    private static long serialVersionUID;

    /**
     * Primary key
     */
    @Id
    @Column(name = "id", nullable = false, unique = true)
    private String id;

    /**
     * Name
     */
    @Column(name ="name", length = 50)
    private String name;

    /**
     * Description
     */
    @Column(name = "description")
    private String description;

    /**
     * element documentation template list
     */
    @JsonManagedReference
    @OneToMany(mappedBy = "documentationTemplate", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<ElementDocumentationTemplateEntity> elementDocumentationTemplates = new ArrayList();


    /**
     * items documentation template list
     */
    @JsonManagedReference
    @OneToMany(mappedBy = "documentationTemplate",fetch = FetchType.LAZY, cascade = CascadeType.REMOVE,orphanRemoval = true)
    private List<ItemDocumentationTemplateEntity> itemDocumentationTemplates = new ArrayList<>();


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public static void setSerialVersionUID(long serialVersionUID) {
        DocumentationTemplateEntity.serialVersionUID = serialVersionUID;
    }

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

    public List<ElementDocumentationTemplateEntity> getElementDocumentationTemplates() {
        return elementDocumentationTemplates;
    }

    public void setElementDocumentationTemplates(List<ElementDocumentationTemplateEntity> elementDocumentationTemplates) {
        this.elementDocumentationTemplates = elementDocumentationTemplates;
    }

    public List<ItemDocumentationTemplateEntity> getItemDocumentationTemplates() {
        return itemDocumentationTemplates;
    }

    public void setItemDocumentationTemplates(List<ItemDocumentationTemplateEntity> itemDocumentationTemplates) {
        this.itemDocumentationTemplates = itemDocumentationTemplates;
    }
}
