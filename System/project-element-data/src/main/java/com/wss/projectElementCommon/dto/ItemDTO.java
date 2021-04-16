package com.wss.projectElementCommon.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * Data Transfer Object for Item
 *
 * @author se7en
 */
public class ItemDTO {

    /**
     * Primary key
     */
    private String id;

    /**
     * name
     */
    private String name;

    /**
     * Material grade name
     */
    private String materialGradeName;

    /**
     * Item list
     */
    @JsonBackReference(value = "itemListDTO-itemDTO")
    private ItemListDTO itemList;

    //@JsonManagedReference
    private ItemTypeDTO itemType;


    public ItemTypeDTO getItemType() {
        return itemType;
    }

    public void setItemType(ItemTypeDTO itemType) {
        this.itemType = itemType;
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

    public ItemListDTO getItemList() {
        return itemList;
    }

    public void setItemList(ItemListDTO itemList) {
        this.itemList = itemList;
    }
}
