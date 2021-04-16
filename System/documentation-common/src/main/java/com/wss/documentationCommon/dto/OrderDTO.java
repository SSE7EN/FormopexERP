package com.wss.documentationCommon.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.wss.documentationCommon.entity.EOrderStatus;

import java.sql.Date;

public class OrderDTO {

    /**
     * Primary key
     */
    private String id;



    @JsonBackReference("documentDTO-orderDTO")
    private DocumentDTO document;

    /**
     * Provider
     */
    @JsonBackReference("providerDTO-orderDTO")
    private DocumentationProviderDTO provider;

    private Date deliveryDate;

    private String description;

    private EOrderStatus orderStatus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



    public DocumentDTO getDocument() {
        return document;
    }

    public void setDocument(DocumentDTO document) {
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

    public DocumentationProviderDTO getProvider() {
        return provider;
    }

    public void setProvider(DocumentationProviderDTO provider) {
        this.provider = provider;
    }
}
