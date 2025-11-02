package com.jihuni.backend_mentoring.business.user;

import com.jihuni.backend_mentoring.business.user.PasswordEncryptor;
import com.jihuni.backend_mentoring.dataaccess.user.UserRepository;
import com.jihuni.backend_mentoring.business.user.User;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final PasswordEncryptor passwordEncryptor;

  public User signup(User user) {
    if (userRepository.existsByEmail(user.getEmail())) {
      throw new IllegalArgumentException("이미 가입된 이메일입니다.");
    }

    String encodedPw = passwordEncryptor.encode(user.getPassword());
    user.changePassword(encodedPw);

    return userRepository.save(user);
  }
}
