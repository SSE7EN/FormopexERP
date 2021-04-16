package com.wss.element.api.request;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.sql.Date;

/**
 * Element request class for creation
 */
public class ElementCreateRequestModel {

    /**
     * Element name
     */
    @Length(max= 55)
    private String name;

    /**
     * Element description
     */
    @Length(max = 255)
    private String description;

    /**
     * Element completion date
     */
    private Date completionDate;

    /**
     * Element count number
     */
    private Integer elementCount = 1;

    /**
     * Element length
     */
    private int length;

    /**
     * Element width
     */
    private int width;

    /**
     * Element height
     */

    private int height;

    /**
     * Project id
     */
    @NotNull(message = "project id cannot be null")
    private String projectId;

    /**
     * ItemList id
     */
    @NotNull(message = "itemList id cannot be null")
    private String itemListId;

    /**
     * ElementType id
     */
    private String elementTypeId;


    public String getElementTypeId() {
        return elementTypeId;
    }

    public void setElementTypeId(String elementTypeId) {
        this.elementTypeId = elementTypeId;
    }

    /**
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name element name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description element description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return completion date
     */
    public Date getCompletionDate() {
        return completionDate;
    }

    /**
     *
     * @param completionDate the completion date
     */
    public void setCompletionDate(Date completionDate) {
        this.completionDate = completionDate;
    }

    /**
     *
     * @return the length
     */
    public int getLength() {
        return length;
    }

    /**
     *
     * @param length the length
     */
    public void setLength(int length) {
        this.length = length;
    }

    /**
     *
     * @return the width
     */
    public int getWidth() {
        return width;
    }

    /**
     *
     * @param width the width
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     *
     * @return the height
     */
    public int getHeight() {
        return height;
    }

    /**
     *
     * @param height the height
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     *
     * @return the project id
     */
    public String getProjectId() {
        return projectId;
    }

    /**
     *
     * @param projectId the project id
     */
    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getItemListId() {
        return itemListId;
    }

    public void setItemListId(String itemListId) {
        this.itemListId = itemListId;
    }

    public Integer getElementCount() {
        return elementCount;
    }

    public void setElementCount(Integer elementCount) {
        this.elementCount = elementCount;
    }
}
