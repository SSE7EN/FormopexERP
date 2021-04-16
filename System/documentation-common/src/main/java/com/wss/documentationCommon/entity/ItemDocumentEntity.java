package com.wss.documentationCommon.entity;


import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@DiscriminatorValue("ItemDocument")
public class ItemDocumentEntity extends DocumentEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManyToMany
    @JoinTable(
            name = "item_document_item_mapping",
            joinColumns = @JoinColumn(name = "item_mapping_id"),
            inverseJoinColumns = @JoinColumn(name = "item_document_id"))
    List<ItemMappingEntity> itemMappings;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public List<ItemMappingEntity> getItemMappings() {
        return itemMappings;
    }

    public void setItemMappings(List<ItemMappingEntity> itemMappings) {
        this.itemMappings = itemMappings;
    }
}
