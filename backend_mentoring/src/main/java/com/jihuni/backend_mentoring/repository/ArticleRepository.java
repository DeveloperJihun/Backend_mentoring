package com.jihuni.backend_mentoring.repository;

import com.jihuni.backend_mentoring.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
}
