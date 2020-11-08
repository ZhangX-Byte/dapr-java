package com.dapr.service.controller;

/**
 * @author Zhang_Xiang
 * @since 2020/11/7 17:24:26
 */

import com.alibaba.fastjson.JSON;
import com.common.ResponseResult;
import io.dapr.client.DaprClient;
import io.dapr.client.domain.HttpExtension;
import lombok.var;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * SpringBoot Controller to handle input binding.
 *
 * @author zhangxiang
 */
@RestController
public class HelloController {

    private final DaprClient client;

    private static final String SERVICE_APP_ID = "java-service-c";


    public HelloController(DaprClient client) {
        this.client = client;
    }

    /**
     * Handles a dapr service invocation endpoint on this app.
     *
     * @param body    The body of the http message.
     * @param headers The headers of the http message.
     * @return A message containing the time.
     */
    @PostMapping(path = "/say")
    public Mono<String> handleMethod(@RequestBody(required = false) byte[] body,
                                     @RequestHeader Map<String, String> headers) {
        return Mono.fromSupplier(() -> {
            try {
                String message = body == null ? "" : new String(body, StandardCharsets.UTF_8);
                String msg = String.format("This is java-service-b,receive the message:%s", message);
                System.out.println(msg);

                var responseResult = new ResponseResult();

                byte[] response = client.invokeService(SERVICE_APP_ID, "say", message, HttpExtension.POST, null,
                        byte[].class).block();
                if (response != null) {
                    msg = String.format("%s,and request java-service-c get the response:%s", msg, new String(response));
                }
                responseResult.setMessage(msg);

                return JSON.toJSONString(responseResult);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

}

