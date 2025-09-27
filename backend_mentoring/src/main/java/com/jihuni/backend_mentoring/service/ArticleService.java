package com.jihuni.backend_mentoring.service;

import com.jihuni.backend_mentoring.entity.Article;
import com.jihuni.backend_mentoring.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    public Article createArticle(Article article) {
        // title, content 유효성 검증
        if (article.getTitle() == null || article.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("제목은 비워둘 수 없습니다.");
        }
        if (article.getContent() == null || article.getContent().trim().isEmpty()) {
            throw new IllegalArgumentException("내용은 비워둘 수 없습니다.");
        }

        return articleRepository.save(article);
    }
}
