package com.wss.authservice.request;

import javax.validation.constraints.NotNull;

public class AppUserCreateRequestModel {

    /**
     * Username
     */
    @NotNull(message = "Username cannot be null")
    private String username;

    /**
     * Password
     */
    @NotNull(message = "Password cannot be null")
    private String password;

    @NotNull(message = "Email cannot be null")
    private String email;

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
}
