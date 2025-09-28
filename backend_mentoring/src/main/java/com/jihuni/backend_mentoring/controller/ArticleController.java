package com.jihuni.backend_mentoring.controller;

import com.jihuni.backend_mentoring.dto.ArticleRequestDto;
import com.jihuni.backend_mentoring.dto.ArticleResponseDto;
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
    public ResponseEntity<ArticleResponseDto> createArticle(@Valid @RequestBody ArticleRequestDto requestDto) {
        ArticleResponseDto responseDto = articleService.createArticle(requestDto);
        return ResponseEntity.ok(responseDto);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<ArticleResponseDto> updateArticle(
            @PathVariable Long id,
            @Valid @RequestBody ArticleRequestDto requestDto) {

        ArticleResponseDto responseDto = articleService.updateArticle(id, requestDto);
        return ResponseEntity.ok(responseDto);
    }
}
