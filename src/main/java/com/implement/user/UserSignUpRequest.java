package com.implement.user;

public record UserSignUpRequest(
        String email,
        String name,
        String password
) {
}
