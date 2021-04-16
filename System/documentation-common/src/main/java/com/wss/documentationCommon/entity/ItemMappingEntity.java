package com.wss.documentationCommon.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;


import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "item_mapping")
public class ItemMappingEntity implements Serializable {

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
    @Column(name = "item_id", nullable = false)
    private String itemId;

    @ManyToMany(mappedBy = "itemMappings")
    List<ItemDocumentEntity> itemDocuments = new ArrayList<>();

    @Column(name ="item_name", nullable = false)
    private String name;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status")
    private EDocumentationStatus status = EDocumentationStatus.INPROGRESS;

    /**
     * Element
     */
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "elementMapping_id")
    private ElementMappingEntity elementMapping;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public List<ItemDocumentEntity> getItemDocuments() {
        return itemDocuments;
    }

    public void setItemDocuments(List<ItemDocumentEntity> itemDocuments) {
        this.itemDocuments = itemDocuments;
    }

    public EDocumentationStatus getStatus() {
        return status;
    }

    public void setStatus(EDocumentationStatus status) {
        this.status = status;
    }

    public ElementMappingEntity getElementMapping() {
        return elementMapping;
    }

    public void setElementMapping(ElementMappingEntity elementMapping) {
        this.elementMapping = elementMapping;
    }
}
