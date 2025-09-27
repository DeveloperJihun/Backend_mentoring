package com.jihuni.backend_mentoring.repository;

import com.jihuni.backend_mentoring.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
}