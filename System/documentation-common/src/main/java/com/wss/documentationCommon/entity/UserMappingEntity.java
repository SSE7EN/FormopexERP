package com.wss.documentationCommon.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_mapping")
public class UserMappingEntity implements Serializable {
    private static long serialVersionUID = 1L;

    /**
     * Primary key
     */
    @Id
    @Column(name = "id", nullable = false, unique = true)
    private String id;

    /**
     * User id
     */
    @Column(name = "user_id", nullable = false, unique = true)
    private String userId;

    @ManyToOne(fetch = FetchType.LAZY)
    private DocumentationProviderEntity provider;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public static void setSerialVersionUID(long serialVersionUID) {
        UserMappingEntity.serialVersionUID = serialVersionUID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public DocumentationProviderEntity getProvider() {
        return provider;
    }

    public void setProvider(DocumentationProviderEntity provider) {
        this.provider = provider;
    }
}
