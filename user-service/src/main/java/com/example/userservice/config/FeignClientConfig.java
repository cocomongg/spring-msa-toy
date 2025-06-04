package com.example.userservice.config;

import com.example.userservice.client.FeignErrorDecoder;
import feign.Logger;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@EnableFeignClients(basePackages = "com.example.userservice.client")
@Configuration
public class FeignClientConfig {

    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public FeignErrorDecoder feignErrorDecoder(Environment env) {
        return new FeignErrorDecoder(env);
    }
}
