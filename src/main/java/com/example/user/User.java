package com.example.user;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@NoArgsConstructor
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String email;

  private String name;

  private String password;

  public User(String password, String name, String email) {
    this.password = password;
    this.name = name;
    this.email = email;
  }
  
  public static User of(String password, String name, String email) {
    return new User(password, name, email);
  }
  
}
