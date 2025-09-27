package com.yunsu.fitmotion.springjwt.controller;

import com.yunsu.fitmotion.springjwt.controller.response.JoinResponse;
import com.yunsu.fitmotion.springjwt.dto.JoinDTO;
import com.yunsu.fitmotion.springjwt.service.JoinService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 회원 가입 요청을 처리하는 컨트롤러
 * - JoinService를 통해 실제 비즈니스 로직 실행
 * - 성공 시 "ok" 문자열 반환
 */
@Controller
@ResponseBody
@Slf4j
public class JoinController {

    private final JoinService joinService;

    public JoinController(JoinService joinService) {

        this.joinService = joinService;
    }

    @PostMapping("/join")
    public ResponseEntity<JoinResponse> joinProcess(JoinDTO joinDTO) {
        log.info("회원가입 요청 수신 - username: {}", joinDTO.getUsername());

        joinService.joinProcess(joinDTO);

        JoinResponse response = JoinResponse.builder()
                .code(200)
                .message("회원가입이 성공적으로 처리되었습니다.")
                .build();

        log.info("회원가입 성공 - username: {}", joinDTO.getUsername());

        return ResponseEntity.ok(response);
    }
}
