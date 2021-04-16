package com.wss.documentationTemplate.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@EntityScan(basePackages = {"com.wss.documentationTemplate.entity"})
public class DocumentationTemplateApplication {


    public static void main(String[] arg){
        SpringApplication.run(DocumentationTemplateApplication.class);
    }

}
