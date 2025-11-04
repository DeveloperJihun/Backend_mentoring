package com.jihuni.backend_mentoring.business.user;

public interface PasswordEncryptor {
  String encode(String rawPassword);
  boolean matches(String rawPassword, String encodedPassword);
}