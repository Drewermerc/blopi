package com.blopi.api.auth.domain.request;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

final public class LogInRequest implements Serializable {
    @NotBlank
    private String username;

    @NotBlank
    private String password;

    public LogInRequest() {
    }

    public LogInRequest(@NotBlank String username, @NotBlank String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
