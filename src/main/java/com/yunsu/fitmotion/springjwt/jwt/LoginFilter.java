package com.yunsu.fitmotion.springjwt.jwt;

import com.yunsu.fitmotion.springjwt.dto.CustomUserDetails;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Collection;
import java.util.Iterator;

/**
 * 로그인 요청을 처리하는 커스텀 필터
 * - UsernamePasswordAuthenticationFilter를 상속
 * - 로그인 성공 시 JWT 발급, 실패 시 401 반환
 */
@Slf4j
public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;

    public LoginFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {

        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    /**
     * 사용자 인증 시도
     * - 요청에서 username, password 추출
     * - AuthenticationManager로 인증 위임
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        String username = obtainUsername(request);
        String password = obtainPassword(request);

        log.info("로그인 시도 - username: {}", username);

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password, null);

        return authenticationManager.authenticate(authToken);
    }


    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) {

        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();

        Long userId = (long) customUserDetails.getUserEntity().getId();
        String username = customUserDetails.getUsername();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
        GrantedAuthority auth = iterator.next();

        String role = auth.getAuthority();

        String token = jwtUtil.createJwt(userId, username, role, 60*60*10L);

        response.addHeader("Authorization", "Bearer " + token);

        log.info("로그인 성공 - username: {}, role: {}, JWT 발급 완료",username,role);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) {

        response.setStatus(401);

        log.warn("로그인 실패 - 사유: {}", failed.getMessage());
    }
}
