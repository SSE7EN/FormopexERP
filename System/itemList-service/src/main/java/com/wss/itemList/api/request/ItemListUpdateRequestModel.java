package com.wss.itemList.api.request;

/**
 * ItemList class for update request
 *
 * @author se7en
 */
public class ItemListUpdateRequestModel extends ItemListCreateRequestModel {

    /**
     * Primary key
     */
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
