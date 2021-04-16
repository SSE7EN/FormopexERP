package com.wss.documentationCommon.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "provider")
public class DocumentationProviderEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * Primary key
     */
    @Id
    @Column(name = "id", nullable = false, unique = true)
    private String id;

    /**
     * Name
     */
    @Column(name = "name")
    private String name;

    /**
     * Provider users
     */
    @OneToMany(mappedBy = "provider", cascade = CascadeType.ALL, orphanRemoval = true)
    List<UserMappingEntity> userMappings = new ArrayList<>();

    @OneToMany(mappedBy = "provider", cascade = CascadeType.ALL, orphanRemoval = true)
    List<OrderEntity> orders = new ArrayList<>();


    public static long getSerialVersionUID() {
        return serialVersionUID;
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

    public List<UserMappingEntity> getUserMappings() {
        return userMappings;
    }

    public void setUserMappings(List<UserMappingEntity> userMappings) {
        this.userMappings = userMappings;
    }

    public List<OrderEntity> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderEntity> orders) {
        this.orders = orders;
    }
}
