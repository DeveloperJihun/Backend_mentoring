package com.jihuni.backend_mentoring.implement.article;

import com.jihuni.backend_mentoring.business.article.Article;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class SpringDataArticleRepositoryImpl implements ArticleRepositoryPort {

  private final SpringDataArticleJpaRepository jpaRepo;

  @Override
  public Article save(Article article) {
    return jpaRepo.save(article);
  }

  @Override
  public Optional<Article> findById(Long id) {
    return jpaRepo.findById(id);
  }
}
