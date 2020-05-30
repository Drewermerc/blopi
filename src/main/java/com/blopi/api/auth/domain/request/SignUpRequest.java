package com.blopi.api.auth.domain.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

final public class SignUpRequest implements Serializable {
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    private Set<String> role;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

    public SignUpRequest(@NotBlank @Size(min = 3, max = 20) String username,
                         @NotBlank @Size(max = 50) @Email String email,
                         @NotBlank @Size(min = 6, max = 40) String password,
                         Set<String> role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public SignUpRequest() {
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }


    public String getPassword() {
        return password;
    }


    public Set<String> getRole() {
        return this.role;
    }

}
