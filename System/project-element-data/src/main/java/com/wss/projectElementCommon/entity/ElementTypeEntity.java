package com.wss.projectElementCommon.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Element type entity
 *
 * @author se7en
 *
 */


@Entity
@Table(name="element_type")
public class ElementTypeEntity {

    private static long serialVersionUID;

    /**
     * Primary key
     */
    @Id
    @Column(name="id", nullable = false, unique = true)
    public String id;

    /**
     * Type name
     */
    @Column(name="name", nullable = false, unique = true, length = 50)
    public String name;

    /**
     * Type description
     */
    @Column(name="description", length = 255)
    public String description;

    /**
     * List of elements
     */
    @JsonBackReference
    @OneToMany(mappedBy = "elementType", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<ElementEntity> elements = new ArrayList<ElementEntity>();


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public static void setSerialVersionUID(long serialVersionUID) {
        ElementTypeEntity.serialVersionUID = serialVersionUID;
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

    public List<ElementEntity> getElements() {
        return elements;
    }

    public void setElements(List<ElementEntity> elements) {
        this.elements = elements;
    }
}
