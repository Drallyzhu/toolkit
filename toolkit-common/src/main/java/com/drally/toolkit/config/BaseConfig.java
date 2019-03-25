package com.drally.toolkit.config;

import com.drally.toolkit.filters.XssFilter;
import com.drally.toolkit.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhujianrong
 * @date 2018-11-14 11:30
 */
@Component
public class BaseConfig implements WebMvcConfigurer {

    @Autowired
    private Environment environment;

    /**
     * 注册XSS工具过滤器
     * @return
     */
    @Bean
    public FilterRegistrationBean xssFilterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new XssFilter());
        filterRegistrationBean.setOrder(1);
        filterRegistrationBean.setEnabled(true);
        filterRegistrationBean.addUrlPatterns("/*");
        Map<String, String> initParameters = new HashMap<>();
        initParameters.put("excludes", "");
        initParameters.put("isIncludeRichText", "true");
        filterRegistrationBean.setInitParameters(initParameters);
        return filterRegistrationBean;
    }

    /**
     * controller 校验器
     * @return
     */
    //基于hibernate的校验包，现在spring自带
//    @Bean
//    public MethodValidationPostProcessor methodValidationPostProcessor(){
//        return new MethodValidationPostProcessor();
//    }

    /**
     * 注册ID生成工具
     * @return
     */
    @Bean
    public IdWorker getIdWorker(){
        long workerId = environment.getProperty("idWorker.workerId")==null?0L:Long.parseLong(environment.getProperty("idWorker.workerId"));
        long datacenterId = environment.getProperty("idWorker.datacenterId")==null?0L:Long.parseLong(environment.getProperty("idWorker.datacenterId"));
        return new IdWorker(workerId,datacenterId);
    }
}
