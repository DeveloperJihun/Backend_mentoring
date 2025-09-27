package com.jihuni.backend_mentoring.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleRequestDto {
    private String email;
    private String password;
    private String title;
    private String content;
}
