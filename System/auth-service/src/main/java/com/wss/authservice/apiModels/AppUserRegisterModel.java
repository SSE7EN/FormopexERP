package com.wss.authservice.apiModels;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class AppUserRegisterModel implements IAppUserApiModel{

    @NotNull
    private String username;

    @NotNull
    private String password;

    @Email
    @NotNull
    private String email;

    public AppUserRegisterModel(@NotNull String username, @NotNull String password, @Email @NotNull String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
