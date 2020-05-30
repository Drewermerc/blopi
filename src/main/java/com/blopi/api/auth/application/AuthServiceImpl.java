package com.blopi.api.auth.application;

import com.blopi.api.auth.application.signin.SignIn;
import com.blopi.api.auth.application.signin.SignInImpl;
import com.blopi.api.auth.application.signup.SignUp;
import com.blopi.api.auth.application.signup.SignUpImpl;
import com.blopi.api.auth.domain.request.LogInRequest;
import com.blopi.api.auth.domain.request.SignUpRequest;
import com.blopi.api.auth.domain.response.MessageResponse;
import com.blopi.api.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final SignUp signUp;
    private final SignIn signIn;

    @Autowired
    public AuthServiceImpl(SignUpImpl signUp, SignInImpl signIn) {
        this.signUp = signUp;
        this.signIn = signIn;
    }

    @Override
    public ResponseEntity<?> authUser(LogInRequest loginRequest) {
        return signIn.authenticateUser(loginRequest);
    }

    @Override
    public ResponseEntity<?> registerUser(SignUpRequest signUpRequest) {
        String response = signUp.existUser(signUpRequest);
        switch ( response ) {
            case "username":
                return ResponseEntity.badRequest()
                                     .body(new MessageResponse("Error: Username is already taken!"));
            case "email":
                return ResponseEntity.badRequest()
                                     .body(new MessageResponse("Error: Email is already in use!"));
            case "ok":
                User user = signUp.createNewUser(signUpRequest);
                signUp.addRole(signUpRequest, user);
                break;
        }

        ResponseEntity.ok();
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

}
