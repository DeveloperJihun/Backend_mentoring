package com.jihuni.backend_mentoring.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleRequestDto {

  @Email(message = "올바른 이메일 형식이 아닙니다.")
  private String email;

  @NotBlank(message = "비밀번호는 비워둘 수 없습니다.")
  private String password;

  @NotBlank(message = "제목은 비워둘 수 없습니다.")
  private String title;

  @NotBlank(message = "내용은 비워둘 수 없습니다.")
  private String content;
}
