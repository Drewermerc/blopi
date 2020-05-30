package com.blopi.api.shared;

import com.blopi.api.auth.domain.request.LogInRequest;

public class LogInRequestMother {
    public static LogInRequest create(String name, String password) {
        return new LogInRequest(name, password);
    }

    public static LogInRequest random() {
        return create(FullNameMother.random(), PasswordMother.random());
    }
}
