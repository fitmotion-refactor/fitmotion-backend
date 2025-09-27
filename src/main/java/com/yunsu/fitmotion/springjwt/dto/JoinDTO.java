package com.yunsu.fitmotion.springjwt.dto;

import com.yunsu.fitmotion.springjwt.common.Role;
import lombok.Getter;
import lombok.Setter;

/**
 * 회원가입 요청 DTO
 * - 클라이언트에서 전달받은 데이터(username, password)
 */
@Setter
@Getter
public class JoinDTO {

    private String username;
    private String password;
    private Role role;
}
