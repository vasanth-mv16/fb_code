package com.facebook.controller;

import com.facebook.model.User;
import com.facebook.service.AuthenticationService;
import com.facebook.service.Impl2.AuthenticationServiceImpl;

/**
 * <p>
 * Given controller act us for request and respond
 * </p>
 *
 * @author vasanth
 * @version 1.0
 */
public class AuthenticationController {

    private static AuthenticationController authenticationController;
    private static final AuthenticationService AUTHENTICATION_SERVICE_IMPL = new AuthenticationServiceImpl();

    /**
     * <p>
     * Enables the creation of only one object at a time
     * </p>
     */
    private AuthenticationController() {
    }

    /**
     * <p>
     * Gets the instance of the authentication controller
     * </p>
     *
     * @return Returns the instance of the authentication controller
     */
    public static AuthenticationController getInstance() {
        if (null == authenticationController) {
            authenticationController = new AuthenticationController();
        }

        return authenticationController;
    }

    /**
     * <p>
     * Checks the user to be created
     * </p>
     *
     * @param user Refers {@link User} has to created
     * @return True if the user is created, false otherwise
     */
    public boolean signUp(final User user) {
        return AUTHENTICATION_SERVICE_IMPL.signUp(user);
    }

    /**
     * <p>
     * Checks the user sign in
     * </p>
     *
     * @param user Refers {@link User} has to sign in
     * @return True if the user is sign in, false otherwise
     */
    public boolean signIn(final User user) {
        return AUTHENTICATION_SERVICE_IMPL.signIn(user);
    }
    /**
     * <p>
     * Retrieves the id of the user
     * </p>
     *
     * @param user Refers {@link User} to get id
     * @return The id of the user
     */
    public Long getUserId(final User user) {
        return AUTHENTICATION_SERVICE_IMPL.getUserId(user);
    }
}
