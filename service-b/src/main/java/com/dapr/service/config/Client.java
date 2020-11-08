package com.dapr.service.config;

import io.dapr.client.DaprClient;
import io.dapr.client.DaprClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Zhang_Xiang
 * @since 2020/11/8 08:46:49
 */
@Configuration
public class Client {

    @Bean
    public DaprClient getClient(){
        return (new DaprClientBuilder()).build();
    }
}
