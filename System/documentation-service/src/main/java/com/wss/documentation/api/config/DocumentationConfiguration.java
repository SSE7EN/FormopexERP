package com.wss.documentation.api.config;

import com.wss.common.security.SecurityConfig;
import com.wss.common.storage.StorageProperties;
import com.wss.common.storage.service.FileSystemStorageService;
import com.wss.common.storage.service.IStorageService;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;


@Configuration
@Import({SecurityConfig.class, FileSystemStorageService.class})

public class DocumentationConfiguration {

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @LoadBalanced
    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }

    @Bean
    public StorageProperties storageProperties(){
        return new StorageProperties();
    }


}
