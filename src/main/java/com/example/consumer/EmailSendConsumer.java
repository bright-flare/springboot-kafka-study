package com.example.consumer;

import com.example.message.EmailSendMessage;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class EmailSendConsumer {

  @KafkaListener(
          topics = "email.send",
          groupId = "email-send-group"
  )
  public void consume(String message) {

    System.out.println("Kafka email send message" + message);
    
    EmailSendMessage emailSendMessage = EmailSendMessage.fromJson(message);
    
    // send to email !!
    System.out.println("email send complete");
  }
  
}
