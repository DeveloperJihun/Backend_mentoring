package com.jihuni.backend_mentoring.implement.user;

import com.jihuni.backend_mentoring.business.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataUserJpaRepository extends JpaRepository<User, Long> {
  boolean existsByEmail(String email);
}
