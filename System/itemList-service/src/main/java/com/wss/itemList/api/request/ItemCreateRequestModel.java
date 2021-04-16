package com.wss.itemList.api.request;

import javax.validation.constraints.NotNull;

/**
 * Item request class for creation
 *
 * @author se7en
 */
public class ItemCreateRequestModel {
    /**
     * Item name
     */
    private String name;

    /**
     *  Material grade name
     */
    private String materialGradeName;

    /**
     * ItemList id
     */
    @NotNull(message = "item list id cannot be null")
    private String itemListId;

    /**
     * Item Type id
     */
    private String itemTypeId;

    public String getItemTypeId() {
        return itemTypeId;
    }

    public void setItemTypeId(String itemTypeId) {
        this.itemTypeId = itemTypeId;
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

    public String getItemListId() {
        return itemListId;
    }

    public void setItemListId(String itemListId) {
        this.itemListId = itemListId;
    }
}
