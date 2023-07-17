package com.facebook.service.Impl2;

import com.facebook.DAO.Impl.UserDAOImpl;
import com.facebook.DAO.UserDAO;
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

    private final UserDAO USER_DAO_IMPL = UserDAOImpl.getInstance();

    /**
     * <p>
     * Updates user details
     * </p>
     *
     * @param user Refers {@link User} to update
     * @return True if the user details are updated, false otherwise
     */
    @Override
    public boolean update(final User user) {
        return USER_DAO_IMPL.update(user);
    }

    /**
     * <p>
     * Deletes user details.
     * </p>
     *
     * @param id Represents the id of the user to be deleted
     * @return True if the user details are successfully deleted, false otherwise
     */
    @Override
    public boolean delete(final Long id) {
        return USER_DAO_IMPL.delete(id);
    }

    /**
     * <p>
     * Retrieves a user by id
     * </p>
     *
     * @param id Represents the id of the user to retrieve
     * @return Returns {@link User} details
     */
    @Override
    public User get(final Long id) {
        return USER_DAO_IMPL.get(id);
    }
}
