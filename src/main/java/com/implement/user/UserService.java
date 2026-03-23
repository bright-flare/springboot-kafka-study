package com.implement.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
  
  private final UserRepository userRepository;
  private final KafkaTemplate<String, String> kafkaTemplate;
  private final ObjectMapper objectMapper;
  
  public void signUp(UserSignUpRequest request) {
    
    User user = User.of(request.email(), request.name(), request.password());
    User savedUser = userRepository.save(user);

    UserSignedUpEvent event = new UserSignedUpEvent(savedUser.getId(), savedUser.getEmail(), savedUser.getName());
    kafkaTemplate.send("user.signed-up", toJsonString(event));
  }
  
  private String toJsonString(Object event) {
    try {
      return objectMapper.writeValueAsString(event);
    } catch (Exception e) {
      throw new RuntimeException("Failed to serialize event", e);
    }
    
  }
  
}
