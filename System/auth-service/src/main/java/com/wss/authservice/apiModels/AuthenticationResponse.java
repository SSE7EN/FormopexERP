package com.wss.authservice.apiModels;

public class AuthenticationResponse {
    private final String jwt;
    private String roles;
    public AuthenticationResponse(String jwt) {
        this.jwt = jwt;
    }
    public AuthenticationResponse(String jwt, String roles) {
        this.jwt = jwt;
        this.roles = roles;
    }

    public String getJwt() {
        return jwt;
    }
    public String getRoles(){
        return roles;
    }



}
