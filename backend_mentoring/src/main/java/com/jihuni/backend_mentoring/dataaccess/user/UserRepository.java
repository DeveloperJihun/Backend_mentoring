package com.jihuni.backend_mentoring.dataaccess.user;

import com.jihuni.backend_mentoring.business.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
  boolean existsByEmail(String email);
  Optional<User> findByEmail(String email);
}
