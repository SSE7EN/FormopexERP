package com.wss.documentationCommon.entity;

/**
 * Enum for documentation status
 *
 * @author se7en
 */
public enum EDocumentationStatus {
    COMPLETED("COMPLETED"), INPROGRESS("IN PROGRESS");

    private String code;

    private EDocumentationStatus(String code){
        this.code = code;
    }

    public String getCode(){
        return code;
    }
}
