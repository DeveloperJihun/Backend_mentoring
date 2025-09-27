package com.jihuni.backend_mentoring.controller;

import com.jihuni.backend_mentoring.entity.Article;
import com.jihuni.backend_mentoring.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @PostMapping("/article")
    public Map<String, Object> createArticle(@RequestBody Article article) {
        Article saved = articleService.createArticle(article);

        Map<String, Object> response = new HashMap<>();
        response.put("articleId", saved.getArticleId());
        response.put("email", saved.getEmail());
        response.put("title", saved.getTitle());
        response.put("content", saved.getContent());

        return response;
    }
}
