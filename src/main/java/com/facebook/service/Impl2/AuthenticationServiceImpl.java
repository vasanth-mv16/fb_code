package com.facebook.service.Impl2;

import com.facebook.DAO.AuthenticationDAO;
import com.facebook.DAO.Impl.AuthenticationDAOImpl;
import com.facebook.model.User;
import com.facebook.service.AuthenticationService;

/**
 * <p>
 * Implements the service for authentication
 * </p>
 *
 * @author vasanth
 * @version 1.0
 */
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationDAO AUTHENTICATION_DAO_IMPL =AuthenticationDAOImpl.getInstance();

    /**
     * <p>
     * Registers a user in the system
     * </p>
     *
     * @param user Refers the user containing the information to be used for sign up
     * @return true if the user is successfully registered, false otherwise
     */
    public boolean signUp(final User user) {
        return AUTHENTICATION_DAO_IMPL.signUp(user);
    }

    /**
     * <p>
     * Authenticates a user by performing a sign-in operation
     * </p>
     *
     * @param user Refers the user containing the need for authentication
     * @return true, if the user is successfully authenticated, false otherwise
     */
    public boolean signIn(final User user) {
        return AUTHENTICATION_DAO_IMPL.signIn(user);
    }

    /**
     * <p>
     * Retrieves the user id associated with the given user
     * </p>
     *
     * @param user Refers the user for which to retrieve the user id
     * @return Returns user id if found, or null if not found or an error occurs
     */
    public Long getUserId(final User user) {
        return AUTHENTICATION_DAO_IMPL.getUserId(user);
    }

}
