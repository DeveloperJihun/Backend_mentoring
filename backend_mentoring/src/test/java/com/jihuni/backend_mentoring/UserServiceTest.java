package com.jihuni.backend_mentoring;

import com.jihuni.backend_mentoring.business.user.User;
import com.jihuni.backend_mentoring.business.user.UserService;
import com.jihuni.backend_mentoring.dataaccess.user.UserRepository;
import com.jihuni.backend_mentoring.business.user.PasswordEncryptor;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserServiceTest {

  private final UserRepository userRepository = Mockito.mock(UserRepository.class);
  private final PasswordEncryptor passwordEncryptor = Mockito.mock(PasswordEncryptor.class);
  private final UserService userService = new UserService(userRepository, passwordEncryptor);

  @Test
  void 회원가입_성공() {
    // given
    User user = new User("test@test.com", "1234", "jihun");
    Mockito.when(userRepository.existsByEmail("test@test.com")).thenReturn(false);
    Mockito.when(passwordEncryptor.encode("1234")).thenReturn("encodedPW");
    Mockito.when(userRepository.save(user)).thenReturn(user);

    // when
    User result = userService.signup(user);

    // then
    assertThat(result.getPassword()).isEqualTo("encodedPW");
  }

  @Test
  void 이미가입된이메일_예외() {
    // given
    User user = new User("test@test.com", "1234", "jihun");
    Mockito.when(userRepository.existsByEmail("test@test.com")).thenReturn(true);

    // expect
    assertThrows(IllegalArgumentException.class, () -> userService.signup(user));
  }
}
