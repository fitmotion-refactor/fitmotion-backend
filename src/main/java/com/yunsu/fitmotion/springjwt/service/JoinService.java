package com.yunsu.fitmotion.springjwt.service;

import com.yunsu.fitmotion.springjwt.dto.JoinDTO;
import com.yunsu.fitmotion.user.infrastructure.UserEntity;
import com.yunsu.fitmotion.springjwt.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 회원가입 처리 서비스
 * - 사용자 존재 여부 확인
 * - 비밀번호 암호화 후 DB 저장
 */
@Service
@Slf4j
public class JoinService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public JoinService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {

        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    /**
     * 회원가입 처리
     * - 사용자 중복 검사
     * - 비밀번호 암호화 후 저장
     */
    public void joinProcess(JoinDTO joinDTO) {

        String username = joinDTO.getUsername();
        String password = joinDTO.getPassword();

        Boolean isExist = userRepository.existsByUsername(username);

        log.info("회원가입 요청 수신 - username: {}", username);


        if (isExist) {
            log.warn("회원가입 실패 - 이미 존재하는 사용자: {}", username);
            return;
        }

        UserEntity data = new UserEntity();

        data.setUsername(username);
        data.setPassword(bCryptPasswordEncoder.encode(password));
        data.setRole(joinDTO.getRole());

        userRepository.save(data);

        log.info("회원가입 성공 - username: {}, role: {}", username, data.getRole());
    }
}
