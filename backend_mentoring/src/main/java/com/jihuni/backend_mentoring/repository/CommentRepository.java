package com.jihuni.backend_mentoring.repository;

import com.jihuni.backend_mentoring.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
