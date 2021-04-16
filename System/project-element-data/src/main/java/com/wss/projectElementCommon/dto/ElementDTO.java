package com.wss.projectElementCommon.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.sql.Date;

/**
 * Data Transfer Object for Element
 *
 * @author se7en
 */
public class ElementDTO {

    /**
     * Primary key
     */
    private String id;

    /**
     * Element name
     */
    private String name;

    /**
     * Element Index Number
     */
    private int elementIndexNumber;

    /**
     * Element description
     */
    private String description;

    /**
     * Element completion date
     */
    private Date completionDate;

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
     * Element type
     */
    private ElementTypeDTO elementType;



    /**
     * Item list
     */
    //@JsonManagedReference(value = "elementDTO-itemListDTO")
    private ItemListDTO itemList;

    public ElementTypeDTO getElementType() {
        return elementType;
    }

    public void setElementType(ElementTypeDTO elementType) {
        this.elementType = elementType;
    }

    public ItemListDTO getItemList() {
        return itemList;
    }

    public void setItemList(ItemListDTO itemList) {
        this.itemList = itemList;
    }

    public int getElementIndexNumber() {
        return elementIndexNumber;
    }

    public void setElementIndexNumber(int elementIndexNumber) {
        this.elementIndexNumber = elementIndexNumber;
    }

    /**
     *
     * @return element id
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(String id) {
        this.id = id;
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
     * @param name
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
     * @param description
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
     * @param completionDate
     */
    public void setCompletionDate(Date completionDate) {
        this.completionDate = completionDate;
    }

    /**
     *
     * @return length
     */
    public int getLength() {
        return length;
    }

    /**
     *
     * @param length
     */
    public void setLength(int length) {
        this.length = length;
    }

    /**
     *
     * @return width
     */
    public int getWidth() {
        return width;
    }

    /**
     *
     * @param width
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     *
     * @return height
     */
    public int getHeight() {
        return height;
    }

    /**
     *
     * @param height
     */
    public void setHeight(int height) {
        this.height = height;
    }









}
