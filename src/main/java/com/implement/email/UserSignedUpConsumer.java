package com.implement.email;

import com.implement.user.UserSignedUpEvent;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserSignedUpConsumer {
  
  private final EmailLogRepository emailLogRepository;
  
  @KafkaListener(
          topics = "user.signed-up",
          groupId = "email-service",
          concurrency = "3"
  )
  @RetryableTopic(
          attempts = "5",
          backoff = @Backoff(delay = 1000, multiplier = 2),
          dltTopicSuffix = ".dlt"
  )
  public void consume(String message) {
    UserSignedUpEvent event = UserSignedUpEvent.fromJson(message);

    String email = event.email();
    String name = event.name();

    String subject = sendEmail(email, name);

    emailLogRepository.save(
            EmailLog.of(event.userId(), email, subject)
    );
    
    
    
  }

  private static String sendEmail(String email, String name) {
    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    
    String subject = "회원가입을 축하드립니다.";
    System.out.println("회원가입 축하 이메일 전송: " + email + ", " + name);
    return subject;
  }

}
