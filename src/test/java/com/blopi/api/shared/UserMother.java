package com.blopi.api.shared;

import com.blopi.api.user.domain.User;

import java.util.concurrent.ThreadLocalRandom;

public final class UserMother {
    public static User create(String username, String email, String password, int option) {
        User user = new User(username, email, password);
        if ( option == 0 ) {
            user.setRoles(RoleMother.createFull());
        }
        if ( option == 1 ) {
            user.setRoles(RoleMother.createAdmin());
        }
        if ( option == 2 ) {
            user.setRoles(RoleMother.createEditor());
        }
        return user;
    }

    public static User random() {
        int randomOption = ThreadLocalRandom.current().nextInt(0, 3);
        return create(FullNameMother.random(), EmailMother.random(), PasswordMother.random(), randomOption);
    }
}
