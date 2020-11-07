package com.dapr.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Dapr's HTTP callback implementation via SpringBoot.
 * Scanning package io.dapr.springboot is required.
 *
 * @author zhangxiang
 */
@SpringBootApplication(scanBasePackages = {"com.dapr.service"})
public class DaprApplication {

    /**
     * Starts Dapr's callback in a given port.
     *
     * @param port Port to listen to.
     */
    public static void start(int port) {
        SpringApplication app = new SpringApplication(DaprApplication.class);
        app.run(String.format("--server.port=%d", port));
    }

}
