package com.wss.documentationProvider.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@EntityScan(basePackages = {"com.wss.documentationCommon.entity"})
public class DocumentationProviderServiceApplication {

    public static void main(String[] arg){
        SpringApplication.run(DocumentationProviderServiceApplication.class);
    }

}
