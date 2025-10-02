package com.jihuni.backend_mentoring.service;

import com.jihuni.backend_mentoring.dto.CommentRequestDto;
import com.jihuni.backend_mentoring.dto.CommentResponseDto;
import com.jihuni.backend_mentoring.entity.Article;
import com.jihuni.backend_mentoring.entity.Comment;
import com.jihuni.backend_mentoring.entity.User;
import com.jihuni.backend_mentoring.repository.ArticleRepository;
import com.jihuni.backend_mentoring.repository.CommentRepository;
import com.jihuni.backend_mentoring.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public CommentResponseDto createComment(Long articleId, CommentRequestDto dto) {
        // 1. 게시글 조회
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));

        // 2. 사용자 확인
        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 이메일입니다."));

        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        // 3. 댓글 생성
        Comment comment = new Comment();
        comment.setEmail(dto.getEmail());
        comment.setContent(dto.getContent());
        comment.setArticle(article);

        // 4. 저장 후 반환
        Comment saved = commentRepository.save(comment);
        return CommentResponseDto.fromEntity(saved);
    }

    @Transactional
    public CommentResponseDto updateComment(Long articleId, Long commentId, CommentRequestDto dto) {
        // 1. 게시글 존재 여부 확인
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));

        // 2. 댓글 조회
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("댓글을 찾을 수 없습니다."));

        // 3. 댓글이 해당 게시글에 속하는지 확인
        if (!comment.getArticle().getArticleId().equals(article.getArticleId())) {
            throw new IllegalArgumentException("해당 게시글에 속한 댓글이 아닙니다.");
        }

        // 4. 사용자 검증
        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 이메일입니다."));

        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        if (!comment.getEmail().equals(dto.getEmail())) {
            throw new IllegalArgumentException("자신의 댓글만 수정할 수 있습니다.");
        }

        // 5. 댓글 내용 수정
        comment.setContent(dto.getContent());

        Comment updated = commentRepository.save(comment);
        return CommentResponseDto.fromEntity(updated);
    }

}
