package com.wss.itemList.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@EntityScan(basePackages = {"com.wss.projectElementCommon.entity"})
public class ItemListServiceApplication {

    public static void main(String[] args){
        SpringApplication.run(ItemListServiceApplication.class);
    }
}
