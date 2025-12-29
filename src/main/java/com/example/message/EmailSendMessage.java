package com.example.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public record EmailSendMessage(
        String from,
        String to,
        String subject,
        String body
) {
  
  public static EmailSendMessage of(String from, String to, String subject, String body) {
    return new EmailSendMessage(from, to, subject, body);
  }
  
  public static EmailSendMessage fromJson(String json) {
    ObjectMapper objectMapper = new ObjectMapper();
    try {
      return objectMapper.readValue(json, EmailSendMessage.class);
    } catch (JsonProcessingException e) {
      throw new RuntimeException("failed to parse email message", e);
    }

  }
  
}
