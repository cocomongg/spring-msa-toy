package com.example.userservice.controller.dto;

import lombok.Getter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class Greeting {

    @Value("${service.greeting.message}")
    private String message;
}
