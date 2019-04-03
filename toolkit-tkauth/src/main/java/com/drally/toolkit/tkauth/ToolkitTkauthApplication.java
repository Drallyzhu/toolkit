package com.drally.toolkit.tkauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;


//feign客户端
@EnableFeignClients
//注册到注册中心
@EnableDiscoveryClient
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@ComponentScan({"com.drally.toolkit.common","com.drally.toolkit.tkauth"})
public class ToolkitTkauthApplication {

    public static void main(String[] args) {
        SpringApplication.run(ToolkitTkauthApplication.class, args);
    }

}
