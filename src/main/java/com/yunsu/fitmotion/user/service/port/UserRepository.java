package com.yunsu.fitmotion.user.service.port;

import com.yunsu.fitmotion.springjwt.common.Role;
import com.yunsu.fitmotion.user.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    Optional<User> findById(Long id);
    Optional<User> findByUsername(String username);
    List<User> findByRole(Role role);
}
