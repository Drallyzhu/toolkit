package com.drally.toolkit.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

//feign客户端
@EnableFeignClients
//注册到注册中心
@EnableDiscoveryClient
@ComponentScan({"com.drally.toolkit.common","com.drally.toolkit.user"})
@SpringBootApplication
public class ToolkitUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(ToolkitUserApplication.class, args);
    }

}
