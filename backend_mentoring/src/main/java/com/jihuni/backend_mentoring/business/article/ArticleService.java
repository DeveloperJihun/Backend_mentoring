package com.jihuni.backend_mentoring.business.article;

import com.jihuni.backend_mentoring.business.user.PasswordEncryptor;
import com.jihuni.backend_mentoring.dataaccess.article.ArticleRepository;
import com.jihuni.backend_mentoring.dataaccess.user.UserRepository;
import com.jihuni.backend_mentoring.dto.ArticleRequestDto;
import com.jihuni.backend_mentoring.dto.ArticleResponseDto;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

@Service
public class ArticleService {

  private final ArticleRepository articleRepository;
  private final UserRepository userRepository;
  private final PasswordEncryptor passwordEncryptor;

  public ArticleService(ArticleRepository articleRepository, UserRepository userRepository, PasswordEncryptor passwordEncryptor) {
    this.articleRepository = articleRepository;
    this.userRepository = userRepository;
    this.passwordEncryptor = passwordEncryptor;
  }

  public ArticleResponseDto createArticle(ArticleRequestDto dto) {
    var encodedPw = passwordEncryptor.encode(dto.getPassword());
    var article = new Article(dto.getEmail(), encodedPw, dto.getTitle(), dto.getContent());
    return ArticleResponseDto.fromEntity(articleRepository.save(article));
  }

  @Transactional
  public ArticleResponseDto updateArticle(Long id, ArticleRequestDto dto) {
    var article = articleRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));

    var user = userRepository.findByEmail(dto.getEmail())
        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 이메일입니다."));

    article.validateOwner(user.getEmail());
    article.update(dto.getTitle(), dto.getContent());

    return ArticleResponseDto.fromEntity(article);
  }
}
