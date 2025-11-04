
package com.jihuni.backend_mentoring.business.article;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name = "articles")
public class Article {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long articleId;

  private String email;
  private String password;
  private String title;
  private String content;

  protected Article() {}

  public Article(String email, String password, String title, String content) {
    this.email = email;
    this.password = password;
    this.title = title;
    this.content = content;
  }

  public void validateOwner(String requestEmail) {
    if (!this.email.equals(requestEmail)) {
      throw new IllegalArgumentException("자신의 게시글만 수정할 수 있습니다.");
    }
  }

  public void update(String title, String content) {
    this.title = title;
    this.content = content;
  }
}
