package com.blopi.api.auth.application.signup;

import com.blopi.api.auth.domain.request.SignUpRequest;
import com.blopi.api.user.domain.User;

public interface SignUp {
    String existUser(SignUpRequest signUpRequest);

    User createNewUser(SignUpRequest signUpRequest);

    void addRole(SignUpRequest signUpRequest, User user);
}
