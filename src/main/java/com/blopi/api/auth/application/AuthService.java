package com.blopi.api.auth.application;

import com.blopi.api.auth.domain.request.LogInRequest;
import com.blopi.api.auth.domain.request.SignUpRequest;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<?> authUser(LogInRequest loginRequest);

    ResponseEntity<?> registerUser(SignUpRequest signUpRequest);
}
