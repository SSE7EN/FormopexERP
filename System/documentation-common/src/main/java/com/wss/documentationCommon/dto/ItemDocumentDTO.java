package com.wss.documentationCommon.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.util.ArrayList;
import java.util.List;

public class ItemDocumentDTO extends DocumentDTO{

    @JsonBackReference(value="itemMappingDTO-itemDocumentDTO")
    private List<ItemMappingDTO> itemMappings = new ArrayList<>();

    public List<ItemMappingDTO> getItemMappings() {
        return itemMappings;
    }

    public void setItemMappings(List<ItemMappingDTO> itemMappings) {
        this.itemMappings = itemMappings;
    }
}
