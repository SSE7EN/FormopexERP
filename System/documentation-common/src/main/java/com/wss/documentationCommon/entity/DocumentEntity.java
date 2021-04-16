package com.wss.documentationCommon.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class DocumentEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Primary key
     */
    @Id
    @Column(name = "id", nullable = false, unique = true)
    private String id;

    /**
     * Documentation
     */
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "documentation_id")
    private DocumentationEntity documentation;

    /**
     * Documents files
     */
    @OneToMany(mappedBy = "document", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<DocumentFileEntity> documentFiles = new ArrayList<>();


    /**
     * Path
     */
    @Column(name = "path", nullable = false)
    private String path;



    /**
     * Name
     */
    @Column(name="name")
    private String name;

    /**
     * Documentation status
     */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status")
    private EDocumentationStatus status = EDocumentationStatus.INPROGRESS;

    /**
     * Documentation status
     */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "delivery_method")
    private EDocumentDeliveryMethod deliveryMethod = EDocumentDeliveryMethod.INHOUSE;


    @OneToOne(mappedBy = "document", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private OrderEntity order;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public DocumentationEntity getDocumentation() {
        return documentation;
    }

    public void setDocumentation(DocumentationEntity documentation) {
        this.documentation = documentation;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EDocumentationStatus getStatus() {
        return status;
    }

    public void setStatus(EDocumentationStatus status) {
        this.status = status;
    }

    public List<DocumentFileEntity> getDocumentFiles() {
        return documentFiles;
    }

    public void setDocumentFiles(List<DocumentFileEntity> documentFiles) {
        this.documentFiles = documentFiles;
    }

    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }

    public EDocumentDeliveryMethod getDeliveryMethod() {
        return deliveryMethod;
    }

    public void setDeliveryMethod(EDocumentDeliveryMethod deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }
}
