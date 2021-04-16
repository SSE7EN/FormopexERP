package com.wss.projectElementCommon.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

/**
 *
 * Elements Entity
 *
 * @author se7en
 *
 */
@Entity
@Table(name = "element")
@NamedEntityGraph(
        name = "element.withProject",
        attributeNodes = {
                @NamedAttributeNode("project")
        }
)
public class ElementEntity implements Serializable {


    private static long serialVersionUID = 1L;

    /**
     * Primary key
     */
    @Id
    @Column(name="id", nullable = false, unique = true)
    private String id;


    /**
     *  Element's name
     */
    @Column(name="name", nullable = true, length = 50)
    private String name;

    /**
     * Element index number
     */
    @Column(name = "element_index_number")
    private Integer elementIndexNumber;


    /**
     *
     * Element type
     */
    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "element_type_id")
    private ElementTypeEntity elementType;


    /**
     *
     * Project
     */
    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private ProjectEntity project;

    /**
     *
     * Project
     */
    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_list_id")
    private ItemListEntity itemList;

    /**
     *
     * Element's description
     */
    @Column(name="description", nullable = true, length = 255)
    private String description;

    /**
     *
     * Element's completion date
     */
    @Column(name="date", nullable = true)
    private Date completionDate;

    /**
     *
     * Element's length
     */
    @Column(name="length", nullable = true)
    private int length;

    /**
     *
     * Element's width
     */
    @Column(name="width", nullable = true)
    private int width;


    /**
     *
     * Element's height
     */
    @Column(name="height", nullable = true)
    private int height;

    public ItemListEntity getItemList() {
        return itemList;
    }

    public void setItemList(ItemListEntity itemList) {
        this.itemList = itemList;
    }

    public static void setSerialVersionUID(long serialVersionUID) {
        ElementEntity.serialVersionUID = serialVersionUID;
    }

    public ElementTypeEntity getElementTypeEntity() {
        return elementType;
    }

    public void setElementType(ElementTypeEntity elementType) {
        this.elementType = elementType;
    }

    public Integer getElementIndexNumber() {
        return elementIndexNumber;
    }

    public void setElementIndexNumber(Integer elementIndexNumber) {
        this.elementIndexNumber = elementIndexNumber;
    }

    public ElementEntity(ElementEntity elementEntity, String id, Integer elementIndexNumber){
        this.id = id;
        this.name = elementEntity.name;
        this.elementIndexNumber = elementIndexNumber;
        this.elementType = elementEntity.elementType;
        this.project = elementEntity.project;
        this.itemList = elementEntity.itemList;
        this.description = elementEntity.description;
        this.completionDate = elementEntity.completionDate;
        this.length = elementEntity.length;
        this.width = elementEntity.width;
        this.height = elementEntity.height;
    }

    public ElementEntity() {}

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

    /**
     *
     * @return project
     */
    public ProjectEntity getProject() {
        return project;
    }

    /**
     *
     * @param projectEntity
     */
    public void setProject(ProjectEntity projectEntity) {
        this.project = projectEntity;
    }

    /**
     *
     * @return serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public ElementTypeEntity getElementType() {
        return elementType;
    }
}
