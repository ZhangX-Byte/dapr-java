package com.dapr.service.controller;

import com.alibaba.fastjson.JSON;
import com.common.ResponseResult;
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

                var responseResult = new ResponseResult();
                String msg = String.format("This is java-service-c,receive the message:%s", message);
                responseResult.setMessage(msg);
                System.out.println(msg);

                return JSON.toJSONString(responseResult);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

}

