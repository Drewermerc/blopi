package com.blopi.api.auth.application.signin;

import com.blopi.api.auth.domain.request.LogInRequest;
import org.springframework.http.ResponseEntity;

public interface SignIn {
    ResponseEntity<?> authenticateUser(LogInRequest loginRequest);
}
