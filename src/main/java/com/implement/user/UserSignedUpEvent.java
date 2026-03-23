package com.implement.user;

public record UserSignedUpEvent(
        Long userId,
        String email,
        String name
) {
  
} 
