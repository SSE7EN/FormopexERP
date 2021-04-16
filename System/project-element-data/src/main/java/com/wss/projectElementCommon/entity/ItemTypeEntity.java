package com.wss.projectElementCommon.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="item_type")
public class ItemTypeEntity {
    private static long serialVersionUID = 1L;

    /**
     * Primary key
     */
    @Id
    @Column(name="id", nullable = false, unique = true)
    private String id;

    /**
     * Type name
     */
    @Column(name="name", nullable = false, unique = true, length = 50)
    private String name;

    /**
     * Type description
     */
    @Column(name="description", length = 255)
    private String description;

    /**
     * List of elements
     */
    @JsonManagedReference
    @OneToMany(mappedBy = "itemType", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<ItemEntity> items = new ArrayList<ItemEntity>();

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public static void setSerialVersionUID(long serialVersionUID) {
        ItemTypeEntity.serialVersionUID = serialVersionUID;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ItemEntity> getItems() {
        return items;
    }

    public void setItems(List<ItemEntity> items) {
        this.items = items;
    }
}
