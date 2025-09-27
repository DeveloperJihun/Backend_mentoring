package com.jihuni.backend_mentoring.controller;

import com.jihuni.backend_mentoring.dto.ArticleRequestDto;
import com.jihuni.backend_mentoring.dto.ArticleResponseDto;
import com.jihuni.backend_mentoring.entity.Article;
import com.jihuni.backend_mentoring.service.ArticleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @PostMapping("/article")
    public ResponseEntity<?> createArticle(@Valid @RequestBody ArticleRequestDto requestDto) {
        try {
            Article saved = articleService.createArticle(requestDto);
            return ResponseEntity.ok(ArticleResponseDto.fromEntity(saved));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(
                    java.util.Map.of("error", e.getMessage())
            );
        }
    }
}
