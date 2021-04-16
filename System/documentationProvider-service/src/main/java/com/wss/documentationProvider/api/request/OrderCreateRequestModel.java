package com.wss.documentationProvider.api.request;

import javax.validation.constraints.NotNull;
import java.sql.Date;

public class OrderCreateRequestModel {

    /**
     * Providers id
     */
    @NotNull(message = "Providers id cannot be null")
    private String providerId;

    /**
     * Document id
     */
    @NotNull(message = "document id cannot be null")
    private String documentId;

    /**
     * Delivery date
     */
    private Date deliveryDate;

    /**
     * Description
     */
    private String description;

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
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
}
