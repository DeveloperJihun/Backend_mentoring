package com.jihuni.backend_mentoring.dataaccess.article;

import com.jihuni.backend_mentoring.business.article.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
