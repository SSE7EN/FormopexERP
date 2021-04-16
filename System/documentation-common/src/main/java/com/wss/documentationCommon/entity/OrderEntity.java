package com.wss.documentationCommon.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
public class OrderEntity  implements Serializable {
    private final long serialVersionUID = 1L;

    /**
     * Primary key
     */
    @Id
    @Column(name = "id", nullable = false, unique = true)
    private String id;

    @OneToOne
    @JoinColumn(name = "document_id")
    private DocumentEntity document;

    @ManyToOne(fetch = FetchType.LAZY)
    private DocumentationProviderEntity provider;

    @Column(name = "delivery_date")
    private Date deliveryDate;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private EOrderStatus orderStatus = EOrderStatus.INPROGRESS;

    public long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public DocumentEntity getDocument() {
        return document;
    }

    public void setDocument(DocumentEntity document) {
        this.document = document;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EOrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(EOrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public DocumentationProviderEntity getProvider() {
        return provider;
    }

    public void setProvider(DocumentationProviderEntity provider) {
        this.provider = provider;
    }
}
