package com.yunsu.fitmotion.user.controller;

import com.yunsu.fitmotion.springjwt.common.Role;
import com.yunsu.fitmotion.user.controller.port.UserService;
import com.yunsu.fitmotion.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    /** ID로 사용자 조회 */
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getById(id));
    }

    /** username으로 사용자 조회 */
    @GetMapping("/username/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        return ResponseEntity.ok(userService.getByUsername(username));
    }

    @GetMapping("/trainers")
    public ResponseEntity<List<User>> getAllTrainers() {
        return ResponseEntity.ok(userService.getByRole(Role.ROLE_TRAINER));
    }
}
