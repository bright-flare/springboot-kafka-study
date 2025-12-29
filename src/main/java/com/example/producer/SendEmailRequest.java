package com.example.producer;

public record SendEmailRequest(
        String from,
        String to,
        String subject,
        String body
) {
  
}