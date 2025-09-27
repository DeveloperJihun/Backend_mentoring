package com.jihuni.backend_mentoring.controller;

import com.jihuni.backend_mentoring.entity.User;
import com.jihuni.backend_mentoring.service.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody User user) {
        try {
            User savedUser = userService.signup(user);

            Map<String, String> response = new HashMap<>();
            response.put("email", savedUser.getEmail());
            response.put("username", savedUser.getUsername());

            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            // ✅ 중복 이메일 등 에러 처리
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

}
