
package com.jihuni.backend_mentoring.presentation.article.dto;

import com.jihuni.backend_mentoring.business.article.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ArticleResponseDto {
    private Long id;
    private String email;
    private String title;
    private String content;

    public static ArticleResponseDto fromEntity(Article article) {
        return new ArticleResponseDto(
            article.getArticleId(),
            article.getEmail(),
            article.getTitle(),
            article.getContent()
        );
    }
}
