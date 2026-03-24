package com.implement.email;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserSignedUpDltConsumer {
  
  private final EmailLogRepository emailLogRepository;

  @KafkaListener(
          topics = "user.signed-up.dlt",
          groupId = "email-service-dlt"
  )
  public void consume(String message) {
    // 로그 전송
    // 알림 전송 등등
  }
}
