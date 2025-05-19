package com.example.secondservice.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/second-service")
@RestController
public class WelcomeController {

    @GetMapping("/welcome")
    public String welcome() {
        return "welcome second service";
    }

    @GetMapping("/message")
    public String message(@RequestHeader("second-request") String requestHeader) {
        log.info("second request header value: {}", requestHeader);
        return "message from second service";
    }

    @GetMapping("/check")
    public String check(HttpServletRequest request) {
        log.info("Server port={}", request.getServerPort());
        return "Hi, there! This is a message from the second service. port=" + request.getServerPort();
    }
}