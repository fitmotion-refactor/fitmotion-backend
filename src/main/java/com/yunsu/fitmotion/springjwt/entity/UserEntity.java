package com.yunsu.fitmotion.springjwt.entity;

import com.yunsu.fitmotion.springjwt.common.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username; // 사용자 아이디
    private String password; // 비밀번호

    @Enumerated(EnumType.STRING) // Enum을 DB에 문자열로 저장
    private Role role; // 권한
}
