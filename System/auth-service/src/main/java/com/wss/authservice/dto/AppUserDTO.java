package com.wss.authservice.dto;

import javax.persistence.Column;
import javax.persistence.Id;

public class AppUserDTO extends AppUserPublicDTO {

    private String password;


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
