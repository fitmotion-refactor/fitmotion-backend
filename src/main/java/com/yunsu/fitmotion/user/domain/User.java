package com.yunsu.fitmotion.user.domain;

import com.yunsu.fitmotion.springjwt.common.Role;
import lombok.Builder;
import lombok.Getter;

@Getter
public class User {

    private final Long id;
    private final String username;
    private final String password;
    private final Role role;

    @Builder
    public User(Long id, String username, String password, Role role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }
}

