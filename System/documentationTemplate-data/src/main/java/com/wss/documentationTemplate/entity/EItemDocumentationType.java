package com.wss.documentationTemplate.entity;

public enum EItemDocumentationType {
    UNIQUE("UNIQUE"), COMMON("COMMON");
    private String code;

    private EItemDocumentationType(String code){
        this.code = code;
    }

    public String getCode(){
        return code;
    }

}
