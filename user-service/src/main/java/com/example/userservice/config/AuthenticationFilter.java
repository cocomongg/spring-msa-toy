package com.example.userservice.config;

import com.example.userservice.controller.dto.UserRequest.LoginRequest;
import com.example.userservice.service.UserService;
import com.example.userservice.service.dto.UserInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final ObjectMapper objectMapper;
    private final UserService userService;
    private final JwtProvider jwtProvider;

    public AuthenticationFilter(AuthenticationManager authenticationManager,
        ObjectMapper objectMapper, UserService userService, JwtProvider jwtProvider) {
        super(authenticationManager);
        this.objectMapper = objectMapper;
        this.userService = userService;
        this.jwtProvider = jwtProvider;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
        HttpServletResponse response) throws AuthenticationException {

        try {
            LoginRequest loginRequest = objectMapper.readValue(request.getInputStream(),
                LoginRequest.class);

            return getAuthenticationManager().authenticate(
                new UsernamePasswordAuthenticationToken(
                    loginRequest.getEmail(),
                    loginRequest.getPassword(),
                    new ArrayList<>()
                )
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
        HttpServletResponse response, FilterChain chain, Authentication authResult)
        throws IOException, ServletException {

        String email = ((User)authResult.getPrincipal()).getUsername();
        UserInfo userInfo = userService.getUserByEmail(email);

        SecretKey secretKey = jwtProvider.getSecretKey();
        Long expirationTime = jwtProvider.getExpirationTime();

        String token = Jwts.builder()
            .subject(userInfo.getUserId())
            .expiration(new Date(System.currentTimeMillis() + expirationTime))
            .signWith(secretKey)
            .compact();

        response.addHeader("token", token);
        response.addHeader("userId", userInfo.getUserId());
    }
}
