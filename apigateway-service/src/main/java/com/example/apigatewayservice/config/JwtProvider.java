package com.example.apigatewayservice.config;

import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@RefreshScope
public class JwtProvider {

    // Config 서버에서 token.secret-key 값을 주입받는다.
    // 프로퍼티가 바뀌면 @RefreshScope로 인해 이 클래스 전체가 재생성된다.
    @Value("${token.secret-key: G3J1bVZFT0w1bWRpWVN3b0dFT3dFc0JvTFhRMTZURlk=}")
    private String secretKeyString;

    @Value("${token.expiration-time:3600000}")
    private Long expirationTime;

    private SecretKey cachedSecretKey;

    @PostConstruct
    public void init() {
        byte[] keyBytes = secretKeyString.getBytes(StandardCharsets.UTF_8);
        this.cachedSecretKey = Keys.hmacShaKeyFor(keyBytes);
    }

    public SecretKey getSecretKey() {
        return this.cachedSecretKey;
    }

    public Long getExpirationTime() {
        return expirationTime;
    }
}
