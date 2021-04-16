package com.wss.projectElementCommon.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * Project Entity
 *
 * @author se7en
 *
 */
@Entity
@Table(name="project")
@NamedEntityGraph(
        name = "project.withElements",
        attributeNodes = {
                @NamedAttributeNode("elements")
        }
)

public class ProjectEntity implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * Primary key
     */
    @Id
    @Column(name ="id", nullable = false, unique = true)
    private String id;


    /**
     *  Project name/title
     */

    @Column(name="name", nullable = false, length = 50)
    private String name;

    /**
     * Project description
     */
    @Column(name = "description", nullable = true, length = 255)
    private String description;

    /**
     * List of elements
     */
    @JsonManagedReference
    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<ElementEntity> elements = new ArrayList<ElementEntity>();

    @JsonManagedReference
    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<ItemListEntity> itemLists = new ArrayList<>();


    public List<ItemListEntity> getItemLists() {
        return itemLists;
    }

    public void setItemLists(List<ItemListEntity> itemLists) {
        this.itemLists = itemLists;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */

    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */

    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return description
     */

    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     */

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return elements
     */

    public List<ElementEntity> getElements() {
        return elements;
    }

    /**
     *
     * @param elements to the elements list
     */
    public void setElements(List<ElementEntity> elements) {
        this.elements = elements;
    }

    /**
     *
     * @return the serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }


}
