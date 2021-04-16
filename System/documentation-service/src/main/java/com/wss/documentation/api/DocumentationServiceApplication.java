package com.wss.documentation.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@EntityScan(basePackages = {"com.wss.documentationCommon.entity"})
public class DocumentationServiceApplication {
    public static void main(String[] arg){
        SpringApplication.run(DocumentationServiceApplication.class);
    }

}
