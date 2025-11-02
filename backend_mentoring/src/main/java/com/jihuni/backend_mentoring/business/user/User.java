package com.jihuni.backend_mentoring.business.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "users") // H2에서 예약어라 테이블명 지정
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true)
  private String email;

  private String username;

  private String password;

  /** ✅ 비밀번호 변경 도메인 메서드 */
  public void changePassword(String encodedPassword) {
    this.password = encodedPassword;
  }

  /** ✅ 생성자 (도메인 규칙 위치) */
  public User(String email, String password, String username) {
    this.email = email;
    this.password = password;
    this.username = username;
  }
}
