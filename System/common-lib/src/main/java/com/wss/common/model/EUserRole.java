package com.wss.common.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum EUserRole {
    ADMIN("ROLE_ADMIN"), EMPLOYEE("ROLE_EMPLOYEE"), OUTSOURCE("ROLE_OUTSOURCE"), USER("ROLE_USER");

    private String code;

    private EUserRole(String code){
        this.code = code;
    }

    @JsonValue
    public String getCode(){
        return code;
    }
}
