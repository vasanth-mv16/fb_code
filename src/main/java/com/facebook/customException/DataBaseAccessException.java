package com.facebook.customException;

public class DataBaseAccessException extends RuntimeException {

    public DataBaseAccessException (final String errorMessage) {
        super(errorMessage);
    }
}
