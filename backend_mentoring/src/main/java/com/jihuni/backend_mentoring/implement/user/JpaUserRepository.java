package com.jihuni.backend_mentoring.implement.user;

import com.jihuni.backend_mentoring.business.user.User;
import com.jihuni.backend_mentoring.dataaccess.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class JpaUserRepository implements UserRepository {

  private final SpringDataUserJpaRepository jpaRepo;

  @Override
  public User save(User user) {
    return jpaRepo.save(user);
  }

  @Override
  public boolean existsByEmail(String email) {
    return jpaRepo.existsByEmail(email);
  }
}
