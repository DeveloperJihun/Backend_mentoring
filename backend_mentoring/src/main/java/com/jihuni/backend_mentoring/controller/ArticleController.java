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
@RequestMapping("/article")
public class ArticleController {

    private final ArticleService articleService;

    // 게시글 작성: POST /article
    @PostMapping
    public ResponseEntity<ArticleResponseDto> createArticle(
            @Valid @RequestBody ArticleRequestDto requestDto) {
        ArticleResponseDto responseDto = articleService.createArticle(requestDto);
        return ResponseEntity.ok(responseDto);
    }

    // 게시글 수정: PUT /article/update/{id}
    @PutMapping("/update/{id}")
    public ResponseEntity<ArticleResponseDto> updateArticle(
            @PathVariable Long id,
            @Valid @RequestBody ArticleRequestDto requestDto) {
        ArticleResponseDto responseDto = articleService.updateArticle(id, requestDto);
        return ResponseEntity.ok(responseDto);
    }

    // 게시글 삭제: DELETE /article/delete/{id}
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteArticle(
            @PathVariable Long id,
            @RequestBody ArticleRequestDto requestDto) {

        articleService.deleteArticle(id, requestDto);
        return ResponseEntity.ok("게시글 및 댓글이 삭제되었습니다.");
    }

}
