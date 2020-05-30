package com.blopi.api.auth.architecture;

import com.blopi.api.auth.application.AuthServiceImpl;
import com.blopi.api.auth.domain.request.LogInRequest;
import com.blopi.api.auth.domain.request.SignUpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api/auth")
public class AuthController {

    private final AuthServiceImpl authService;

    @Autowired
    public AuthController(AuthServiceImpl authService) {
        this.authService = authService;
    }


    @PostMapping("/signin")
    public ResponseEntity<?> signin(@Valid @RequestBody LogInRequest loginRequest) {
        return authService.authUser(loginRequest);
    }

    @PostMapping("/signup")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> signup(@Valid @RequestBody SignUpRequest signUpRequest) {
        return authService.registerUser(signUpRequest);
    }

}
