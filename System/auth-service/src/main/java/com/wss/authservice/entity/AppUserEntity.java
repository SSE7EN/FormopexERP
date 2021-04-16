package com.wss.authservice.entity;


import com.wss.authservice.apiModels.AppUserRegisterModel;
import com.wss.common.model.EUserRole;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
public class AppUserEntity implements Serializable {

    private static long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false, unique = true)
    private String id;

    @Column(name = "username",nullable = false)
    private String username;

    @Column(name = "password",nullable = false)
    private String password;

    @Column(name = "email",nullable = false, unique = true)
    private String email;

    private boolean active;

    private String roles ="";

    private String permissions = "";

    public AppUserEntity(String username, String password, String email, boolean active, String roles, String permissions) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.active = active;
        this.roles = roles;
        this.permissions = permissions;
    }
    public AppUserEntity(AppUserRegisterModel appUserRegisterModel, String roles){
        this.username = appUserRegisterModel.getUsername();
        this.password = appUserRegisterModel.getPassword();
        this.email = appUserRegisterModel.getEmail();
        this.active = true;
        this.roles = roles;
    }

    public AppUserEntity(String username, String password, String email, boolean active, EUserRole role, String permissions) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.active = active;
        this.roles = role.getCode();
        this.permissions = permissions;
    }
    public AppUserEntity(AppUserRegisterModel appUserRegisterModel, EUserRole role){
        this.username = appUserRegisterModel.getUsername();
        this.password = appUserRegisterModel.getPassword();
        this.email = appUserRegisterModel.getEmail();
        this.active = true;
        this.roles = role.getCode();
    }

    protected AppUserEntity(){};



    public List<String> getRoleList(){

        if(this.roles.length() > 0){
            return Arrays.asList(this.roles.split(","));
        }
        return new ArrayList<>();
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public static void setSerialVersionUID(long serialVersionUID) {
        AppUserEntity.serialVersionUID = serialVersionUID;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getPermissions() {
        return permissions;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }

    public List<String> getPermissionList(){

        if(this.permissions.length() > 0){
            return Arrays.asList(this.permissions.split(","));
        }
        return new ArrayList<>();
    }

    public void setId(String id) {
        this.id = id;
    }
}
