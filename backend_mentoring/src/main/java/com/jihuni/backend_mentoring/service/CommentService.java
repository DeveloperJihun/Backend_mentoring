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
}
