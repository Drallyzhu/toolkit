package com.drally.toolkit;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class ToolkitGetewayApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(ToolkitGetewayApplication.class).web(true).run(args);
    }

}
