package com.wss.authservice.apiModels;

public class AuthenticationRequest {
    String email;
    String password;


    public AuthenticationRequest(){};

    public AuthenticationRequest(String password, String email) {
        this.password = password;
        this.email = email;
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
