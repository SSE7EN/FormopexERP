package com.wss.documentationCommon.dto;

import java.sql.Date;

/**
 * DTO for ElementDocumentation
 *
 * @author se7en
 */
public class ElementDocumentationDTO {
    /**
     * Primary key
     */
    private String id;

    /**
     * Element id
     */
    private String elementId;

    /**
     * Documentation status as String
     */
    private String status;

    /**
     * Completion date
     */
    private Date date;

    /**
     * Name
     */
    private String name;

    /**
     * Documentation template id
     */
    private String documentationTemplateId;

    public String getDocumentationTemplateId() {
        return documentationTemplateId;
    }

    public void setDocumentationTemplateId(String documentationTemplateId) {
        this.documentationTemplateId = documentationTemplateId;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getElementId() {
        return elementId;
    }

    public void setElementId(String elementId) {
        this.elementId = elementId;
    }
}
