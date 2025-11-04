
package com.jihuni.backend_mentoring.presentation.article;

import com.jihuni.backend_mentoring.business.article.ArticleService;
import com.jihuni.backend_mentoring.presentation.article.dto.ArticleRequestDto;
import com.jihuni.backend_mentoring.presentation.article.dto.ArticleResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/article")
public class ArticleController {

    private final ArticleService articleService;

    @PostMapping
    public ResponseEntity<ArticleResponseDto> createArticle(@Valid @RequestBody ArticleRequestDto requestDto) {
        return ResponseEntity.ok(articleService.createArticle(requestDto));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ArticleResponseDto> updateArticle(
            @PathVariable Long id,
            @Valid @RequestBody ArticleRequestDto requestDto) {
        return ResponseEntity.ok(articleService.updateArticle(id, requestDto));
    }
}
