package com.wss.documentationCommon.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "element_mapping")
public class ElementMappingEntity {

    private static final long serialVersionUID = 1L;

    /**
     * Primary key
     */
    @Id
    @Column(name = "id", unique = true, nullable = false)
    private String id;

    /**
     * Item id
     */
    @Column(name = "element_id", nullable = false, unique = true)
    private String elementId;

    @Column(name ="element_name", nullable = false)
    private String name;

    /**
     *  Project id
     */
    @Column(name = "project_id")
    private String projectId;

    @OneToMany(mappedBy = "elementMapping", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<ItemMappingEntity> itemMappings = new ArrayList<>();

    @ManyToMany(mappedBy = "elementMappings")
    List<ElementDocumentEntity> elementDocuments;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status")
    private EDocumentationStatus status = EDocumentationStatus.INPROGRESS;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getElementId() {
        return elementId;
    }

    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    public List<ElementDocumentEntity> getElementDocuments() {
        return elementDocuments;
    }

    public void setElementDocuments(List<ElementDocumentEntity> elementDocuments) {
        this.elementDocuments = elementDocuments;
    }

    public EDocumentationStatus getStatus() {
        return status;
    }

    public void setStatus(EDocumentationStatus status) {
        this.status = status;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public List<ItemMappingEntity> getItemMappings() {
        return itemMappings;
    }

    public void setItemMappings(List<ItemMappingEntity> itemMappings) {
        this.itemMappings = itemMappings;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
