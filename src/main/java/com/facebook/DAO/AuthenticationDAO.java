package com.facebook.DAO;

import com.facebook.model.User;

/**
 * <p>
 * Provides a service DAO for authentication
 * </p>
 *
 * @author vasanth
 * @version 1.1
 */
public interface AuthenticationDAO {

    /**
     * <p>
     * Registers user sign up details in the database
     * </p>
     *
     * @param user Refers {@link User} has to sign up
     * @return True if the user is sign up, false otherwise
     */
    boolean signUp(final User user);

    /**
     * <p>
     * Validates user sign-in details in the database
     * </p>
     *
     * @param user Refer {@link User} to sign in
     * @return True if the sign-in is successful, false otherwise
     */
    boolean signIn(final User user);

    /**
     * <p>
     * Retrieves user id from the database
     * </p>
     *
     * @param user Refers {@link User} to get the id
     * @return Returns the id of the user
     */
    Long getUserId(final User user);
}
