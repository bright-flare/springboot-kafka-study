package com.example.producer;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EmailApi {
  
  private final EmailService emailService;

  @PostMapping("/api/emails")
  public String post(@RequestBody SendEmailRequest request) {
    emailService.sendEmail(request);
    return "email sent";
  }
  
}
