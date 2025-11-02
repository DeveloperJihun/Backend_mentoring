package com.jihuni.backend_mentoring.dataaccess.user;

import com.jihuni.backend_mentoring.business.user.User;

public interface UserRepository {

  User save(User user);

  boolean existsByEmail(String email);
}
