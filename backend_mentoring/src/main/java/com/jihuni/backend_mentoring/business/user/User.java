package com.jihuni.backend_mentoring.business.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "users")
public class User {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String email;
  private String password;
  private String username;

  public User(String email, String password, String username) {
    this.email = email;
    this.password = password;
    this.username = username;
  }

  public void changePassword(String encodedPw) {
    this.password = encodedPw;
  }
}
