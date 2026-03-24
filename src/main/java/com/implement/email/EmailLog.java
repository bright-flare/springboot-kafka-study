package com.implement.email;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "email_logs")
public class EmailLog {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  private String receiverEmail;
  
  private Long receiverUserId;
  
  private String subject;
  
  private String body;

  public EmailLog() {

  }

  public EmailLog(String receiverEmail, Long receiverUserId, String subject) {
    this.receiverEmail = receiverEmail;
    this.receiverUserId = receiverUserId;
    this.subject = subject;
  }

  public static EmailLog of(Long userId, String email, String subject) {
    return new EmailLog(email, userId, subject);
  };
  
}
