package com.yunsu.fitmotion.user.controller.port;

import com.yunsu.fitmotion.springjwt.common.Role;
import com.yunsu.fitmotion.user.domain.User;

import java.util.List;

public interface UserService {
    User getById(Long id);
    User getByUsername(String username);
    List<User> getByRole(Role role);
}
