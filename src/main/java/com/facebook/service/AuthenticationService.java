package com.facebook.service;

import com.facebook.model.User;

/**
 * <p>
 * Provides a service for authentication
 * </p>
 *
 * @author vasanth
 * @version 1.0
 */
public interface AuthenticationService {

    /**
     * <p>
     * Adds user details.
     * </p>
     *
     * @param user Refers {@link User} to add
     * @return True if the user is successfully added, false otherwise
     */
    boolean signUp(final User user);

    /**
     * <p>
     * Validates user sign-in details.
     * </p>
     *
     * @param user Refer {@link User} to sign in
     * @return True if the sign-in is successful, false otherwise
     */
    boolean signIn(final User user);

    /**
     * <p>
     * Retrieves user id
     * </p>
     *
     * @param user Refers {@link User} to get the id
     * @return Returns the id of the user
     */
    Long getUserId(final User user);
}
