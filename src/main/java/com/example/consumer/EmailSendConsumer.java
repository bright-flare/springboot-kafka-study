package com.example.consumer;

import com.example.message.EmailSendMessage;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Service;

@Service
public class EmailSendConsumer {

  @KafkaListener(
          topics = "email.send",
          groupId = "email-send-group"
  )
  @RetryableTopic(
          attempts = "5", // 총 시도 횟수
          backoff = @Backoff(
                  delay = 1000, // 재시도 간격 (밀리초)
                  multiplier = 2.0 // 지수 백오프 배율 1초, 2초, 4초, 8초, ...
          ),
          dltTopicSuffix = ".dlt" // Dead letter topic의 접미사
          // DLT 토픽 확인하기 : bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic email.send.dlt --from-beginning
  )
  public void consume(String message) {
    
    System.out.println("Kafka email send message" + message);
    
    EmailSendMessage emailSendMessage = EmailSendMessage.fromJson(message);

    if (emailSendMessage.to().equals("fail@test.com")) {
      throw new RuntimeException("잘못된 이메일주소로 인해 이메일 전송에 실패했습니다.");
    }

    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      throw new RuntimeException("email send error", e);
    }

    System.out.println("email send complete");
  }
  
}
