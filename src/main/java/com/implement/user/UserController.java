package com.implement.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;
  
  @PostMapping
  public ResponseEntity<String> signUp(@RequestBody UserSignUpRequest request) {
    userService.signUp(request);
    return ResponseEntity.ok("User signed up successfully");
  }
}
