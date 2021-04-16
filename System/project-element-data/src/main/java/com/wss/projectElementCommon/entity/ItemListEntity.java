package com.wss.projectElementCommon.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Item List entity
 *
 * @author se7en
 */

@Entity
@Table(name="item_list")
public class ItemListEntity implements Serializable {
    private static long serialVersionUID;

    /**
     * Primary key
     */
    @Id
    @Column(name="id", unique = true, nullable = false)
    private String id;


    /**
     * Name
     */
    @Column(name = "name")
    private String name;

    /**
     * Description
     */
    @Column(name="description")
    private String description;


//    /**
//     * List of elements
//     */
//    @JsonBackReference
//    @OneToMany(mappedBy = "itemList", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
//    private List<ElementEntity> elements = new ArrayList<ElementEntity>();


    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="project_id")
    private ProjectEntity project;

    /**
     * List of items
     */
    @JsonManagedReference
    @OneToMany(mappedBy = "itemList", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<ItemEntity> items = new ArrayList<>();

    public ProjectEntity getProject() {
        return project;
    }

    public void setProject(ProjectEntity project) {
        this.project = project;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public static void setSerialVersionUID(long serialVersionUID) {
        ItemListEntity.serialVersionUID = serialVersionUID;
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
