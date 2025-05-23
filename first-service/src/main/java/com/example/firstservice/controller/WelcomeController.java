package com.example.firstservice.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/first-service")
@RestController
public class WelcomeController {

    @GetMapping("/welcome")
    public String welcome() {
        return "welcome first service";
    }

    @GetMapping("/message")
    public String message(@RequestHeader("first-request") String requestHeader) {
        log.info("first request header value: {}", requestHeader);
        return "message from first service";
    }

    @GetMapping("/check")
    public String check(HttpServletRequest request) {
        log.info("Server port={}", request.getServerPort());
        return "Hi, there! This is a message from the first service. port=" + request.getServerPort();
    }
}
