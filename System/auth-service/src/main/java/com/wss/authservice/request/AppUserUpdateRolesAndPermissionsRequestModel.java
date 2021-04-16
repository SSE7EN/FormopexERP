package com.wss.authservice.request;

import com.wss.common.model.EUserPermission;
import com.wss.common.model.EUserRole;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AppUserUpdateRolesAndPermissionsRequestModel {

    private String id;

    private Set<EUserRole> roles = new HashSet<>();

    private Set<EUserPermission> permissions = new HashSet<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Set<EUserRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<EUserRole> roles) {
        this.roles = roles;
    }

    public Set<EUserPermission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<EUserPermission> permissions) {
        this.permissions = permissions;
    }
}
