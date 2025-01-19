package com.example.talabati.Exceptions;


public class ProfileSettingsNotFound extends RuntimeException {
    public ProfileSettingsNotFound(String message) {
        super(message);
    }
}