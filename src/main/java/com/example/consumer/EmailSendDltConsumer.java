package com.example.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmailSendDltConsumer {

  /**
   * Dead Letter Topic(DLT) 메시지 소비
   * 
   * 1. 로그 기록
   * 2. 알림 발송
   * 3. 수동 재처리
   */
  @KafkaListener(
          topics = "email.send.dlt",
          groupId = "email-send-dlt-group"
  )
  public void consume(String message) {
    log.info("DLT로 이동한 메시지: {}", message);
    log.info("Salck 알림 발송: {}", message);
  }
  
}
