package com.wss.projectElementCommon.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.ArrayList;
import java.util.List;

/**
 * Data Transfer Object for Item list
 *
 * @author se7en
 */
public class ItemListDTO {

    /**
     * Primary key
     */
    private String id;

    /**
     * Name
     */
    private String name;

    /**
     * Description
     */
    private String description;

    /**
     * ProjectDTO
     */
    @JsonBackReference(value = "projectDTOWEAIL-itemListDTO")
    private ProjectDTO project;


    /**
     * List of items
     */
    @JsonManagedReference(value = "itemListDTO-itemDTO")
    private List<ItemDTO> items = new ArrayList<>();

    public ProjectDTO getProject() {
        return project;
    }

    public void setProject(ProjectDTO project) {
        this.project = project;
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

    public List<ItemDTO> getItems() {
        return items;
    }

    public void setItems(List<ItemDTO> items) {
        this.items = items;
    }
}
