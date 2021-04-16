package com.wss.projectElementCommon.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;

/**
 * item entity
 *
 * @author se7en
 */

@Entity
@Table(name = "item")
public class ItemEntity implements Serializable {

    private static long serialVersionUID;

    /**
     * Primary key
     */
    @Id
    @Column(name = "id",nullable = false, unique = true)
    private String id;

    /**
     * Name
     */
    @Column(name="name", nullable = false)
    private String name;

    /**
     * Material grade name
     */
    @Column(name="material_grade_name", nullable = false)
    private String materialGradeName;

    /**
     * Item list
     */
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="item_list_id")
    private ItemListEntity itemList;

    /**
     *
     * Element type
     */
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_type_id")
    private ItemTypeEntity itemType;






    public ItemTypeEntity getItemType() {
        return itemType;
    }

    public void setItemType(ItemTypeEntity itemType) {
        this.itemType = itemType;
    }

    public ItemListEntity getItemList() {
        return itemList;
    }

    public void setItemList(ItemListEntity itemList) {
        this.itemList = itemList;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public static void setSerialVersionUID(long serialVersionUID) {
        ItemEntity.serialVersionUID = serialVersionUID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMaterialGradeName() {
        return materialGradeName;
    }

    public void setMaterialGradeName(String materialGradeName) {
        this.materialGradeName = materialGradeName;
    }
}
