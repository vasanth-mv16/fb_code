package com.facebook.customException;

/**
 * <p>
 * Shows Exception, when file name does not exits
 * </p>
 */
public class WrongFileNameException extends Exception {
    public WrongFileNameException(final String errorMessage) {
        super(errorMessage);
    }
}

