package com.yunsu.fitmotion.user.infrastructure;

import com.yunsu.fitmotion.springjwt.common.Role;
import com.yunsu.fitmotion.user.domain.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    public static UserEntity from(User user) {
        UserEntity entity = new UserEntity();
        entity.id = user.getId();
        entity.username = user.getUsername();
        entity.password = user.getPassword();
        entity.role = user.getRole();
        return entity;
    }

    public User toModel() {
        return User.builder()
                .id(id)
                .username(username)
                .password(password)
                .role(role)
                .build();
    }
}
