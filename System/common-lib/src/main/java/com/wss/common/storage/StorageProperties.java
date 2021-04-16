package com.wss.common.storage;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.stereotype.Component;



public class StorageProperties {

    /**
     * Folder location for storing files
     */
    private String location = "upload-dir/";

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public StorageProperties(String location) {
        this.location = location;
    }

    public StorageProperties() {
    }
}