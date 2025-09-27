package com.yunsu.fitmotion.user.infrastructure;

import com.yunsu.fitmotion.springjwt.common.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JpaUserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);
    List<UserEntity> findByRole(Role role);
}

