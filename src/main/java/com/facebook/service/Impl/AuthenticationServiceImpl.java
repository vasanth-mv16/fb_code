package com.facebook.service.Impl;

import com.facebook.model.User;
import com.facebook.service.AuthenticationService;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Implements the service for authentication
 * </p>
 *
 * @author vasanth
 * @version 1.0
 */
public class AuthenticationServiceImpl implements AuthenticationService {

    public static AuthenticationService authenticationServiceImpl;
    public static final List<User> USER_LIST = new ArrayList<>();

    /**
     * <p>
     * Enables the creation of only one object at a time
     * </p>
     */
    private AuthenticationServiceImpl() {
    }

    /**
     * <p>
     * Gets the instance of the authentication service implementation
     * </p>
     *
     * @return Returns the instance of the authentication implementation
     */
    public static AuthenticationService getInstance() {
        if (null == authenticationServiceImpl) {
            authenticationServiceImpl = new AuthenticationServiceImpl();
        }

        return authenticationServiceImpl;
    }

    /**
     * <p>
     * Adds user details
     * </p>
     *
     * @param user Refers {@link User} to add
     * @return True if the user is successfully added, false otherwise
     */
    @Override
    public boolean signUp(final User user) {
        for (final User existingUser : USER_LIST) {
            return !(existingUser.getMobileNumber().equals(user.getMobileNumber()));
        }

        return USER_LIST.add(user);
    }

    /**
     * <p>
     * Validates user sign-in details
     * </p>
     *
     * @param user Refer {@link User} to sign in
     * @return True if the sign-in is successful, false otherwise
     */
    @Override
    public boolean signIn(final User user) {
        if (null != user.getEmail()) {
            return isEmailExist(user);
        } else {
            return isMobileNumberExist(user);
        }
    }

    /**
     * <p>
     * Retrieves user id
     * </p>
     *
     * @param user Refers {@link User} to get the id
     * @return Returns the id of the user
     */
    @Override
    public Long getUserId(final User user) {
        for (final User existingUser : USER_LIST) {

            if (existingUser.getName().equals(user.getName())) {
                return existingUser.getId();
            }
        }

        return null;
    }

    /**
     * <p>
     * Checks the users mobile number and password exists in the list
     * </p>
     *
     * @param user {@link User}user object containing the mobile number and password
     * @return true if a user with the given mobile number and password is found, otherwise false
     */
    public boolean isMobileNumberExist(final User user) {
        for (User existingUser : USER_LIST) {

            if ((user.getMobileNumber().equals(existingUser.getMobileNumber()) &&
                    user.getPassword().equals(existingUser.getPassword()))) {
                return true;
            }
        }

        return false;
    }

    /**
     * <p>
     * Checks the users email and password exists in the list
     * </p>
     *
     * @param user {@link User}user object containing the mobile number and password
     * @return true if a user with the given mobile number and password is found, otherwise false
     */
    public boolean isEmailExist(final User user) {
        for (User existingUser : USER_LIST) {

            if (existingUser.getEmail().equals(user.getEmail()) &&
                    existingUser.getPassword().equals(user.getPassword())) {
                return true;
            }
        }

        return false;
    }
}
