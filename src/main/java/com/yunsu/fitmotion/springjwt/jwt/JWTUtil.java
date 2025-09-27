package com.yunsu.fitmotion.springjwt.jwt;

import com.yunsu.fitmotion.springjwt.common.Role;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * JWT 생성 및 검증 유틸 클래스
 * - 토큰 생성, 파싱, 만료 여부 확인 기능 제공
 */
@Component
@Slf4j
public class JWTUtil {

    private SecretKey secretKey;

    public JWTUtil(@Value("${spring.jwt.secret}")String secret) {

        this.secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), Jwts.SIG.HS256.key().build().getAlgorithm());
    }

    /**
     * userId 추출
     * */
    public Long getUserId(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get("userId", Long.class);
    }

    /**
     * 토큰에서 username 추출
     */
    public String getUsername(String token) {

        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("username", String.class);
    }

    /**
     * 토큰에서 role 추출
     */
    public Role getRole(String token) {

        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("role", Role.class);
    }

    /**
     * 토큰 만료 여부 확인
     */
    public Boolean isExpired(String token) {

        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().getExpiration().before(new Date());
    }

    /**
     * JWT 생성
     * - username, role, 만료 시간(ms) 포함
     */
    public String createJwt(Long userId, String username, String role, Long expiredMs) {

        String token = Jwts.builder()
                .claim("userId",userId)
                .claim("username", username)
                .claim("role", role)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiredMs))
                .signWith(secretKey)
                .compact();

        log.info("JWT 생성 완료 - username: {}, role: {}, 만료(ms): {}", username, role, expiredMs);
        return token;
    }
}
