package com.facebook.controller;

import com.facebook.model.User;
import com.facebook.service.Impl2.UserServiceImpl;
import com.facebook.service.UserService;

/**
 * <p>
 * Given controller act us for request and respond
 * </p>
 *
 * @author vasanth
 * @version 1.0
 */
public class UserController {

    private static UserController userController;
    private static final UserService USER_SERVICE_IMPL = new UserServiceImpl();

    /**
     * <p>
     * Enables the creation of only one object at a time
     * </p>
     */
    private UserController() {
    }

    /**
     * <p>
     * Gets the instance of the user controller
     * </p>
     *
     * @return Returns the instance of the controller
     */
    public static UserController getInstance() {
        if (null == userController) {
            userController = new UserController();
        }

        return userController;
    }

    /**
     * <p>
     * Updates the user details
     * </p>
     *
     * @param user Refers {@link User} has to updated
     * @return True if the user is updated, false otherwise
     */
    public boolean update(final User user) {
        return USER_SERVICE_IMPL.update(user);
    }

    /**
     * <p>
     * Checks the user delete details
     * </p>
     *
     * @param id Represents the user id to delete
     * @return True if the user is deleted, false otherwise
     */
    public boolean delete(final Long id) {
        return USER_SERVICE_IMPL.delete(id);
    }

    /**
     * <p>
     * Gets the user detail
     * </p>
     *
     * @param id Represents the user through id
     * @return Returns {@link User} details
     */
    public User get(final Long id) {
        return USER_SERVICE_IMPL.get(id);
    }

}

