package com.wss.documentationTemplate.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Entity for Item documentation template
 *
 * @author se7en
 */
@Entity
@Table(name = "item_documentation_template",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"documentation_template_id", "name"})}
)
public class ItemDocumentationTemplateEntity implements Serializable {

    private static long serialVersionUID = 1L;

    /**
     * Primary key
     */
    @Id
    @Column(name="id", nullable = false, unique = true)
    private String id;



    /**
     * Documentation
     */
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="documentation_template_id")
    private DocumentationTemplateEntity documentationTemplate;

    /**
     * Item Documentation type
     */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "item_documentation_type")
    private EItemDocumentationType itemDocumentationType = EItemDocumentationType.UNIQUE;

    /**
     * Item type id
     */
    @Column(name = "item_type_id")
    private String itemTypeId;

    @Column(name = "name", length = 50)
    private String name;

    @Column(name="description")
    private String description;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public static void setSerialVersionUID(long serialVersionUID) {
        ItemDocumentationTemplateEntity.serialVersionUID = serialVersionUID;
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

    public EItemDocumentationType getItemDocumentationType() {
        return itemDocumentationType;
    }

    public void setItemDocumentationType(EItemDocumentationType itemDocumentationType) {
        this.itemDocumentationType = itemDocumentationType;
    }

    public String getItemTypeId() {
        return itemTypeId;
    }

    public void setItemTypeId(String itemTypeId) {
        this.itemTypeId = itemTypeId;
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


