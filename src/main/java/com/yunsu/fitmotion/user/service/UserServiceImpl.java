package com.yunsu.fitmotion.user.service;

import com.yunsu.fitmotion.springjwt.common.Role;
import com.yunsu.fitmotion.user.controller.port.UserService;
import com.yunsu.fitmotion.user.domain.User;
import com.yunsu.fitmotion.user.service.port.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found: " + id));
    }

    @Override
    public User getByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found: " + username));
    }

    @Override
    public List<User> getByRole(Role role) {
        return userRepository.findByRole(role);
    }
}
