package com.example.email.producer;

import com.example.user.message.EmailSendMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {
  
  private final KafkaTemplate<String, String> kafkaTemplate;
  private final ObjectMapper objectMapper;
  
  public void sendEmail(SendEmailRequest request) {
    EmailSendMessage message = EmailSendMessage.of(
            request.from(),
            request.to(),
            request.subject(),
            request.body()
    );
    
    kafkaTemplate.send("email.send", toJsonString(message));
    
  }
  
  private String toJsonString(Object object) {
    try {
      return objectMapper.writeValueAsString(object);
    } catch (JsonProcessingException e) {
      throw new RuntimeException("failed to convert object to json string", e);
    }
  }
  
}
