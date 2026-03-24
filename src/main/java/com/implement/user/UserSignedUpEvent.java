package com.implement.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public record UserSignedUpEvent(
        Long userId,
        String email,
        String name
) {
  
  public static UserSignedUpEvent fromJson(String json) {
    try {
      ObjectMapper objectMapper = new ObjectMapper();
      return objectMapper.readValue(json, UserSignedUpEvent.class);
    } catch (JsonProcessingException e) {
      throw new RuntimeException("failed to parse user signed up event", e);
    }
  }
} 
