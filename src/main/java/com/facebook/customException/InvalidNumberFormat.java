package com.facebook.customException;

public class InvalidNumberFormat extends RuntimeException {

    public InvalidNumberFormat (final String errorMessage) {
        super(errorMessage);
    }
}
