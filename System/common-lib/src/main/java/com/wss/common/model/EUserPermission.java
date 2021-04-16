package com.wss.common.model;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum EUserPermission {

    PROVIDE_DOCUMENTATION("PROVIDE_DOCUMENTATION"), TEST("TEST");

    private String code;

    private EUserPermission(String code){
        this.code = code;
    }

    @JsonValue
    public String getCode(){
        return code;
    }

    public static EUserPermission getEnum(String code){
        List<EUserPermission> enums = Arrays.asList(EUserPermission.values());
        for (EUserPermission tEnum: enums) {
            if(tEnum.getCode().equals(code)) return tEnum;
        }

        return null;
    }


}
