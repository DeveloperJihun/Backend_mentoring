package com.jihuni.backend_mentoring.dto;

import com.jihuni.backend_mentoring.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommentResponseDto {

  private Long commentId;
  private String email;
  private String content;

  public static CommentResponseDto fromEntity(Comment comment) {
    return new CommentResponseDto(
        comment.getCommentId(),
        comment.getEmail(),
        comment.getContent()
    );
  }
}
