package com.example.email.producer;

public record SendEmailRequest(
        String from,
        String to,
        String subject,
        String body
) {
  
}