package com.jihuni.backend_mentoring.controller;

import com.jihuni.backend_mentoring.dto.ArticleRequestDto;
import com.jihuni.backend_mentoring.dto.ArticleResponseDto;
import com.jihuni.backend_mentoring.entity.Article;
import com.jihuni.backend_mentoring.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @PostMapping("/article")
    public ResponseEntity<?> createArticle(@RequestBody ArticleRequestDto requestDto) {
        try {
            // DTO → Entity 변환
            Article article = new Article();
            article.setEmail(requestDto.getEmail());
            article.setPassword(requestDto.getPassword());
            article.setTitle(requestDto.getTitle());
            article.setContent(requestDto.getContent());

            Article saved = articleService.createArticle(article);

            // Entity → DTO 변환
            ArticleResponseDto responseDto = new ArticleResponseDto(
                    saved.getArticleId(),
                    saved.getEmail(),
                    saved.getTitle(),
                    saved.getContent()
            );

            return ResponseEntity.ok(responseDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(
                    java.util.Map.of("error", e.getMessage())
            );
        }
    }
}
