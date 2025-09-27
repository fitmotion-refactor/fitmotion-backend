package com.yunsu.fitmotion.springjwt.jwt;


import com.yunsu.fitmotion.springjwt.common.Role;
import com.yunsu.fitmotion.springjwt.dto.CustomUserDetails;
import com.yunsu.fitmotion.springjwt.entity.UserEntity;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * JWT 검증 필터
 * - 모든 요청마다 실행되어 Authorization 헤더에서 JWT를 추출하고 검증
 * - 유효한 경우 SecurityContext에 Authentication 저장
 */
@Slf4j
public class JWTFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;

    public JWTFilter(JWTUtil jwtUtil) {

        this.jwtUtil = jwtUtil;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //request에서 Authorization 헤더를 찾음
        String authorization= request.getHeader("Authorization");

        //Authorization 헤더 검증
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            log.debug("JWT 없음 또는 잘못된 Authorization 헤더 : {}", request.getRequestURI());
            filterChain.doFilter(request, response);
            return;
        }

        // 토큰 값 추출
        String token = authorization.split(" ")[1];

        //토큰 소멸 시간 검증
        if (jwtUtil.isExpired(token)) {
            log.warn("만료된 JWT - uri: {}", request.getRequestURI());
            filterChain.doFilter(request, response);
            return;
        }

        // 토큰에서 사용자 정보 추출
        String username = jwtUtil.getUsername(token);
        Role role = jwtUtil.getRole(token);

        // 유저 엔티티 생성 (패스워드는 임의)
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(username);
        userEntity.setPassword("temppassword");
        userEntity.setRole(role);

        CustomUserDetails customUserDetails = new CustomUserDetails(userEntity);

        Authentication authToken = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authToken);

        log.info("JWT 인증 성공 - usermane: {} role: {}", username, role);

        filterChain.doFilter(request, response);
    }
}
