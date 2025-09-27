package com.jihuni.backend_mentoring.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ArticleResponseDto {
    private Long articleId;
    private String email;
    private String title;
    private String content;
}
