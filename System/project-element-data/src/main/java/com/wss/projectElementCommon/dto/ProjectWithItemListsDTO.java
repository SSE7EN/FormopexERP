package com.wss.projectElementCommon.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.ArrayList;
import java.util.List;

public class ProjectWithItemListsDTO extends ProjectDTO {
    /**
     * list of item lists
     */
    private List<ItemListDTO> itemLists = new ArrayList<>();

    public List<ItemListDTO> getItemLists() {
        return itemLists;
    }

    public void setItemLists(List<ItemListDTO> itemLists) {
        this.itemLists = itemLists;
    }


}
