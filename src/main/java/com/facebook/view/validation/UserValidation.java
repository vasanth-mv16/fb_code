package com.facebook.view.validation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

/**
 * <p>
 * Given class used for validation the user details
 * </p>
 *
 * @author vasanth
 * @version 1.0
 */
public class UserValidation {

    private static UserValidation userValidation;

    /**
     * <p>
     * Enables the creation of only one object at a time
     * </p>
     */
    private UserValidation() {
    }

    /**
     * <p>
     * Gets the instance of the user validation
     * </p>
     *
     * @return Returns instance of the user validation class
     */
    public static UserValidation getInstance() {
        if (null == userValidation) {
            userValidation = new UserValidation();
        }

        return userValidation;
    }

    /**
     * <p>
     * Validates a name string using a regular expression pattern
     * </p>
     *
     * @param name - The name string to be validated
     * @return True if the name is valid, false otherwise
     */
    public boolean validateName(final String name) {
        return name.matches("^[a-zA-Z\\s]+\\.?");
    }

    /**
     * <p>
     * Validates a mobile number string using a regular expression pattern
     * </p>
     *
     * @param mobileNumber - The mobile Number string to be validated
     * @return True if the mobileNumber is valid, false otherwise
     */
    public boolean validateMobileNumber(final String mobileNumber) {
        return mobileNumber.matches("(^\\+(91){1,2}[6-9][0-9]{9}$)");
    }

    /**
     * <p>
     * Validates a email string using a regular expression pattern
     * </p>
     *
     * @param email The email string to be validated
     * @return True if the email is valid, false otherwise
     */
    public boolean validateEmail(final String email) {
        return email.matches("^[a-zA-Z][a-zA-Z0-9]{1,15}@[a-z]{3,15}\\.[com[org[edu[in]]]]{2,3}$");
    }

    /**
     * <p>
     * Validates a password string using a regular expression pattern
     * </p>
     *
     * @param password The password string to be validated
     * @return True if the password is valid, false otherwise
     */
    public boolean validatePassword(final String password) {
        return password.matches("^(?=.*[\\d])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$");
    }

    /**
     * <p>
     * Validates a date of birth string using a regular expression pattern
     * </p>
     *
     * @param dateOfBirth The date of birth has to validated
     * @return True if the date of birth is valid, false otherwise
     */
    public boolean validateDateOfBirth(final String dateOfBirth) {
        try {
            final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-uuuu")
                                                   .withResolverStyle(ResolverStyle.STRICT);
            final LocalDate localDate = LocalDate.parse(dateOfBirth, dateFormatter);
            final LocalDate presentDate = LocalDate.now();
            final LocalDate previousYear = LocalDate.MIN;

            if (localDate.isBefore(previousYear.withYear(1900)) || localDate.isAfter(presentDate)) {
                return false;
            }

            return true;
        } catch (final DateTimeParseException exception) {
            return false;
        }
    }

    /**
     * <p>
     * Validates a choice string using a regular expression pattern
     * </p>
     *
     * @param choice The choice has to validated
     * @return True if the choice is valid, false otherwise
     */
    public boolean validateChoice(final String choice) {
        return choice.matches("\\d{1,2}");
    }

    /**
     * <p>
     * Validates a check string using a regular expression pattern
     * </p>
     *
     * @param access The access to be validated
     * @return True if the check is valid, false otherwise
     */
    public boolean validateAccess(final String access) {
        return (access.equalsIgnoreCase("yes") || access.equalsIgnoreCase("y"));
    }

    /**
     * <p>
     * Validates a userId string using a regular expression pattern
     * </p>
     *
     * @param userId The user id to be validated
     * @return True if the user id is valid, false otherwise
     */
    public boolean validateUserId(final String userId) {
        return userId.matches("[\\d]");
    }

}
