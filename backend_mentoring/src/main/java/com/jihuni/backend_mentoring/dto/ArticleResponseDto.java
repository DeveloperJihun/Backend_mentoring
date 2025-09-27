package com.jihuni.backend_mentoring.dto;

import com.jihuni.backend_mentoring.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ArticleResponseDto {
    private Long articleId;
    private String email;
    private String title;
    private String content;

    // 정적 팩토리 메서드
    public static ArticleResponseDto fromEntity(Article article) {
        return new ArticleResponseDto(
                article.getArticleId(),
                article.getEmail(),
                article.getTitle(),
                article.getContent()
        );
    }
}
