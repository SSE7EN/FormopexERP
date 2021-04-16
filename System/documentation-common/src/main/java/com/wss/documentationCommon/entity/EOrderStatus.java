package com.wss.documentationCommon.entity;

public enum EOrderStatus {

    COMPLETED("COMPLETED"), INPROGRESS("IN PROGRESS");

    private String code;

    private EOrderStatus(String code){
        this.code = code;
    }

    public String getCode(){
        return code;
    }
}
