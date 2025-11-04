package com.jihuni.backend_mentoring.implement.article;

import com.jihuni.backend_mentoring.business.article.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataArticleJpaRepository extends JpaRepository<Article, Long> {
}
