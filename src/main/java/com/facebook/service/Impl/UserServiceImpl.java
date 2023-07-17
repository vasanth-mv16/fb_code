package com.facebook.service.Impl;

import com.facebook.model.User;
import com.facebook.service.UserService;

/**
 * <p>
 * Implements the following services for the user
 * </p>
 *
 * @author vasanth
 * @version 1.0
 */
public class UserServiceImpl implements UserService {

    private static UserService userServiceImpl;

    /**
     * <p>
     * Enables the creation of only one object at a time
     * </p>
     */
    private UserServiceImpl() {
    }

    /**
     * <p>
     * Gets the instance of the user service implementation
     * </p>
     *
     * @return Returns the instance of the user implementation
     */
    public static UserService getInstance() {
        if (null == userServiceImpl) {
            userServiceImpl = new UserServiceImpl();
        }

        return userServiceImpl;
    }

    /**
     * {@inheritDoc}
     *
     * @param user Refers {@link User} to be update
     * @return True if the user details are updated, false otherwise
     */
    public boolean update(final User user) {
        for (User existingUser : AuthenticationServiceImpl.USER_LIST) {

            if (existingUser.getId().equals(user.getId())) {
                existingUser.setName(user.getName());
                existingUser.setEmail(user.getEmail());
                existingUser.setPassword(user.getPassword());
                existingUser.setMobileNumber(user.getMobileNumber());

                return true;
            }
        }

        return false;
    }

    /**
     * {@inheritDoc}
     *
     * @param user Refers {@link User} to sign in
     * @return True if the sign-in is successful, false otherwise
     */
    public boolean signIn(final User user) {
        if (null != user.getEmail()) {
            return isEmailExist(user);
        } else {
            return isMobileNumberExist(user);
        }
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
        for (User existingUser : AuthenticationServiceImpl.USER_LIST) {

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
        for (User existingUser : AuthenticationServiceImpl.USER_LIST) {

            if (existingUser.getEmail().equals(user.getEmail()) &&
                    existingUser.getPassword().equals(user.getPassword())) {
                return true;
            }
        }

        return false;
    }

    /**
     * {@inheritDoc}
     *
     * @param id Represents the id of the user to be deleted
     * @return True if the user details are successfully deleted, false otherwise
     */
    public boolean delete(final Long id) {
        final User user = get(id);

        if (null != user) {
            return AuthenticationServiceImpl.USER_LIST.remove(user);
        }

        return false;
    }

    /**
     * {@inheritDoc}
     *
     * @param id Represents the id of the user to retrieve
     * @return Returns {@link User} through id
     */
    public User get(final Long id) {
        for (final User existingUser : AuthenticationServiceImpl.USER_LIST) {

            if (existingUser.getId().equals(id)) {
                return existingUser;
            }
        }

        return null;
    }
}

