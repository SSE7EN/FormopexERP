package com.wss.documentationCommon.entity;

public enum EDocumentDeliveryMethod {

    INHOUSE("IN HOUSE"), OUTSOURCE("OUTSOURCE");

    private String code;

    private EDocumentDeliveryMethod(String code){
        this.code = code;
    }

    public String getCode(){
        return code;
    }
}
