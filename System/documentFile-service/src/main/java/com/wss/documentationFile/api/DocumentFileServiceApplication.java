package com.wss.documentationFile.api;

import com.wss.common.storage.StorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;


@EnableDiscoveryClient
@SpringBootApplication
@EntityScan(basePackages = {"com.wss.documentationCommon.entity"})
public class DocumentFileServiceApplication {

    public static void main(String[] arg){
        SpringApplication.run(DocumentFileServiceApplication.class);
    }
}
