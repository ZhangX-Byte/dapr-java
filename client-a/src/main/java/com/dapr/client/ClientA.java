package com.dapr.client;

import com.alibaba.fastjson.JSON;
import com.common.ResponseResult;
import io.dapr.client.DaprClient;
import io.dapr.client.DaprClientBuilder;
import io.dapr.client.domain.HttpExtension;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

/**
 * @author Zhang_Xiang
 * @since 2020/11/7 17:30:26
 */
public class ClientA {
    /**
     * Identifier in Dapr for the service this client will invoke.
     */
    private static final String SERVICE_APP_ID = "java-service-b";

    /**
     * Format to output date and time.
     */
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    /**
     * Starts the invoke client.
     *
     * @param args Messages to be sent as request for the invoke API.
     */
    public static void main(String[] args) throws IOException {
        try (DaprClient client = (new DaprClientBuilder()).build()) {
            while (true) {
                Calendar utcNow = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
                String utcNowAsString = DATE_FORMAT.format(utcNow.getTime());
                String msg = String.format("%s:this this java client A", utcNowAsString);
                byte[] response = client.invokeService(SERVICE_APP_ID, "say", msg.getBytes(), HttpExtension.POST, null,
                        byte[].class).block();
                if (response != null) {
                    String responseResultStr = new String(response);
                    ResponseResult responseResult = JSON.parseObject(responseResultStr, ResponseResult.class);
                    System.out.println(responseResult.getMessage());
                }
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
