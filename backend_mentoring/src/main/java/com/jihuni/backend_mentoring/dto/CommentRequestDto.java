package com.jihuni.backend_mentoring.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentRequestDto {

  @NotBlank(message = "이메일은 비워둘 수 없습니다.")
  private String email;

  @NotBlank(message = "비밀번호는 비워둘 수 없습니다.")
  private String password;

  @NotBlank(message = "내용은 비워둘 수 없습니다.")
  private String content;
}
