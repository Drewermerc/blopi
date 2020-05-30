package com.blopi.api.shared;

public final class PasswordMother {
    public static String random() {
        return MotherCreator.random().internet().password();
    }
}
