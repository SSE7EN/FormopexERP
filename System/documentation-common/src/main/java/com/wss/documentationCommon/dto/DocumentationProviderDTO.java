package com.wss.documentationCommon.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.ArrayList;
import java.util.List;

public class DocumentationProviderDTO {

    /**
     * Primary key
     */
    private String id;

    /**
     * Name
     */
    private String name;

    /**
     * User mappings
     */
    @JsonManagedReference("providerDTO-userMappingDTO")
    private List<UserMappingDTO> userMappings = new ArrayList<>();

    /**
     * Orders
     */
    @JsonManagedReference("providerDTO-orderDTO")
    private List<OrderDTO> orders = new ArrayList<>();

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

    public List<UserMappingDTO> getUserMappings() {
        return userMappings;
    }

    public void setUserMappings(List<UserMappingDTO> userMappings) {
        this.userMappings = userMappings;
    }

    public List<OrderDTO> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderDTO> orders) {
        this.orders = orders;
    }
}
