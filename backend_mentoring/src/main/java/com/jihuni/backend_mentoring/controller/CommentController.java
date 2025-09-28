package com.jihuni.backend_mentoring.controller;

import com.jihuni.backend_mentoring.dto.CommentRequestDto;
import com.jihuni.backend_mentoring.dto.CommentResponseDto;
import com.jihuni.backend_mentoring.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/article")
public class CommentController {

    private final CommentService commentService;

    // 댓글 작성
    @PostMapping("/{id}/comments")
    public ResponseEntity<CommentResponseDto> createComment(
            @PathVariable Long id,
            @Valid @RequestBody CommentRequestDto requestDto) {

        CommentResponseDto responseDto = commentService.createComment(id, requestDto);
        return ResponseEntity.ok(responseDto);
    }
}
