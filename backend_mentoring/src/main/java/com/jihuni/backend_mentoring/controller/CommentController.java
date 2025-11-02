package com.jihuni.backend_mentoring.controller;

import com.jihuni.backend_mentoring.dto.CommentRequestDto;
import com.jihuni.backend_mentoring.dto.CommentResponseDto;
import com.jihuni.backend_mentoring.service.CommentService;
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
public class CommentController {

  private final CommentService commentService;

  // 댓글 작성
  @PostMapping("/{articleId}/comments")
  public ResponseEntity<CommentResponseDto> createComment(
      @PathVariable Long articleId,
      @Valid @RequestBody CommentRequestDto requestDto) {

    CommentResponseDto responseDto = commentService.createComment(articleId, requestDto);
    return ResponseEntity.ok(responseDto);
  }

  // 댓글 수정
  @PutMapping("/{articleId}/comments/{commentId}/update")
  public ResponseEntity<CommentResponseDto> updateComment(
      @PathVariable Long articleId,
      @PathVariable Long commentId,
      @Valid @RequestBody CommentRequestDto requestDto) {

    CommentResponseDto responseDto = commentService.updateComment(articleId, commentId, requestDto);
    return ResponseEntity.ok(responseDto);
  }

}
