package com.blopi.api.shared;

import com.blopi.api.auth.domain.request.SignUpRequest;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class SignUpRequestMother {
    public static SignUpRequest create(String username, String email, String password, int option) {
        SignUpRequest request = new SignUpRequest();
        if ( option == 0 ) {
            request = new SignUpRequest(username, email, password, adminRole());
        }
        if ( option == 1 ) {
            request = new SignUpRequest(username, email, password, adminRole());
        }
        if ( option == 2 ) {
            request = new SignUpRequest(username, email, password, adminRole());
        }

        return request;
    }

    public static Set<String> adminRole() {
        Set<String> roles = new HashSet<>();
        roles.add("admin");
        return roles;
    }

    public static Set<String> editorRole() {
        Set<String> roles = new HashSet<>();
        roles.add("editor");
        return roles;
    }

    public static Set<String> fullRole() {
        Set<String> roles = new HashSet<>();
        roles.add("admin");
        roles.add("editor");
        return roles;
    }

    public static SignUpRequest random() {
        int randomOption = ThreadLocalRandom.current().nextInt(0, 3);
        return create(FullNameMother.random(), EmailMother.random(), PasswordMother.random(), randomOption);
    }
}
