package com.blopi.api.user.application;

import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<?> getDashboard(String userName, String token);
}
