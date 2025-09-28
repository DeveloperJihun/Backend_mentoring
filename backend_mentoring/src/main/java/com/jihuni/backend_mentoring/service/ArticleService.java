package com.jihuni.backend_mentoring.service;

import com.jihuni.backend_mentoring.dto.ArticleRequestDto;
import com.jihuni.backend_mentoring.dto.ArticleResponseDto;
import com.jihuni.backend_mentoring.entity.Article;
import com.jihuni.backend_mentoring.entity.User;
import com.jihuni.backend_mentoring.repository.ArticleRepository;
import com.jihuni.backend_mentoring.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public ArticleResponseDto createArticle(ArticleRequestDto dto) {
        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 이메일입니다."));

        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        Article article = new Article();
        article.setEmail(dto.getEmail());
        article.setPassword(dto.getPassword());
        article.setTitle(dto.getTitle());
        article.setContent(dto.getContent());

        Article saved = articleRepository.save(article);


        return ArticleResponseDto.fromEntity(saved);
    }
    @Transactional
    public ArticleResponseDto updateArticle(Long id, ArticleRequestDto dto) {
        // 1. 게시글 조회
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));

        // 2. 사용자 조회
        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 이메일입니다."));

        // 3. 비밀번호 검증
        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        // 4. 본인 글 확인 (email 비교)
        if (!article.getEmail().equals(user.getEmail())) {
            throw new IllegalArgumentException("자신의 게시글만 수정할 수 있습니다.");
        }

        // 5. 수정
        article.setTitle(dto.getTitle());
        article.setContent(dto.getContent());

        // 6. DTO 변환 후 반환
        return ArticleResponseDto.fromEntity(article);
    }


    
}
