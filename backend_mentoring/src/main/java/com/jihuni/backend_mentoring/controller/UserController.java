package com.jihuni.backend_mentoring.controller;

import com.jihuni.backend_mentoring.dto.UserRequestDto;
import com.jihuni.backend_mentoring.dto.UserResponseDto;
import com.jihuni.backend_mentoring.entity.User;
import com.jihuni.backend_mentoring.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody UserRequestDto requestDto) {
        try {
            // DTO → Entity 변환
            User user = new User();
            user.setEmail(requestDto.getEmail());
            user.setPassword(requestDto.getPassword());
            user.setUsername(requestDto.getUsername());

            User savedUser = userService.signup(user);

            // Entity → Response DTO 변환
            UserResponseDto responseDto = new UserResponseDto(
                    savedUser.getEmail(),
                    savedUser.getUsername()
            );

            return ResponseEntity.ok(responseDto);
        } catch (IllegalArgumentException e) {
            // ✅ 중복 이메일 등 에러 처리
            return ResponseEntity.badRequest().body(
                    java.util.Map.of("error", e.getMessage())
            );
        }
    }
}
