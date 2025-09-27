package com.jihuni.backend_mentoring.service;

import com.jihuni.backend_mentoring.entity.Article;
import com.jihuni.backend_mentoring.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.jihuni.backend_mentoring.repository.UserRepository;
import com.jihuni.backend_mentoring.entity.User;
import org.springframework.security.crypto.password.PasswordEncoder;


@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder; // 주입

    public Article createArticle(Article article) {
        User user = userRepository.findByEmail(article.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 이메일입니다."));

        // 암호화된 비번 비교
        if (!passwordEncoder.matches(article.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        if (article.getTitle() == null || article.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("제목은 비워둘 수 없습니다.");
        }
        if (article.getContent() == null || article.getContent().trim().isEmpty()) {
            throw new IllegalArgumentException("내용은 비워둘 수 없습니다.");
        }

        return articleRepository.save(article);
    }
}
