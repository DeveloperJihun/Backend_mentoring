package com.jihuni.backend_mentoring.service;

import com.jihuni.backend_mentoring.dto.ArticleRequestDto;
import com.jihuni.backend_mentoring.entity.Article;
import com.jihuni.backend_mentoring.entity.User;
import com.jihuni.backend_mentoring.repository.ArticleRepository;
import com.jihuni.backend_mentoring.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public Article createArticle(ArticleRequestDto dto) {
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

        return articleRepository.save(article);
    }
}
