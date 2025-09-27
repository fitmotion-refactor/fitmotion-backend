package com.yunsu.fitmotion.springjwt.service;


import com.yunsu.fitmotion.springjwt.dto.CustomUserDetails;
import com.yunsu.fitmotion.springjwt.entity.UserEntity;
import com.yunsu.fitmotion.springjwt.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 사용자 정보를 DB에서 조회해 인증 과정에 제공하는 서비스
 * - Spring Security의 UserDetailsService 구현체
 * - 로그인 시 username 기반으로 사용자 엔티티를 조회
 */
@Service
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    /**
     * username으로 사용자 정보를 조회
     * - DB에서 UserEntity 조회
     * - 존재할 경우 CustomUserDetails로 감싸서 반환
     * - 없을 경우 null 반환
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        log.info("로그인 시도 - username: {}", username);

        UserEntity userData = userRepository.findByUsername(username);

        if (userData != null) {
            log.info("사용자 정보 로딩 성공 - username: {}, role: {}", userData.getUsername(), userData.getRole());
            return new CustomUserDetails(userData);
        }

        log.warn("로그인 실패 - 존재하지 않는 사용자: {}", username);
        return null;
    }
}
