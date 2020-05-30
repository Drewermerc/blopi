package com.blopi.api.shared;

public final class EmailMother {
    public static String random() {
        return MotherCreator.random().internet().emailAddress();
    }
}
