package com.jihuni.backend_mentoring.presentation.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDto {
    private String email;
    private String password;
    private String username;
}
