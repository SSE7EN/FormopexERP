package com.wss.projectElementCommon.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.ArrayList;
import java.util.List;

public class ProjectWithElementsAndItemListsDTO extends ProjectDTO{

    /**
     * List of elements
     */
    private List<ElementDTO> elements = new ArrayList<ElementDTO>();

    /**
     * list of item lists
     */
    @JsonManagedReference(value = "projectDTOWEAIL-itemListDTO")
    private List<ItemListDTO> itemLists = new ArrayList<>();

    public List<ElementDTO> getElements() {
        return elements;
    }

    public void setElements(List<ElementDTO> elements) {
        this.elements = elements;
    }

    public List<ItemListDTO> getItemLists() {
        return itemLists;
    }

    public void setItemLists(List<ItemListDTO> itemLists) {
        this.itemLists = itemLists;
    }
}
