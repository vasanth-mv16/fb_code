package com.facebook.DAO.Impl;

import com.facebook.DAO.UserDAO;
import com.facebook.DAOConnection.DatabaseAccessConnection;
import com.facebook.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * <p>
 * Provides the following services for the user
 * </p>
 *
 * @author vasanth
 * @version 1.1
 */
public class UserDAOImpl implements UserDAO {

    private static UserDAOImpl userDAOImpl;

    /**
     * <p>
     * Enables the creation of only one object at a time
     * </p>
     */
    public UserDAOImpl() {
    }

    /**
     * <p>
     * Gets the instance of user service implementation
     * </p>
     *
     * @return Returns the singleton instance of the user service implementation class.
     */
    public static UserDAOImpl getInstance() {
        if (null == userDAOImpl) {
            userDAOImpl = new UserDAOImpl();
        }

        return userDAOImpl;
    }

    /**
     * <p>
     * Retrieves a user from the database through id
     * </p>
     *
     * @param id Retrieves the id of the user
     * @return Returns {@link User}with the specified ID if found, otherwise null
     */
    public User get(final Long id) {
        final String sql = "select * from users where id = ?";

        try (final Connection connection = DatabaseAccessConnection.getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            connection.setAutoCommit(false);
            preparedStatement.setLong(1, id);
            final ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                final User user = new User();

                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setEmail(resultSet.getString("email"));
                user.setGender(User.Gender.valueOf(resultSet.getString("gender")));
                user.setDateOfBirth(resultSet.getString("dateofbirth"));
                user.setPassword(resultSet.getString("password"));
                user.setMobileNumber(resultSet.getString("phonenumber"));
                connection.commit();
                DatabaseAccessConnection.releaseConnection(connection);

                return user;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return null;
    }

    /**
     * <p>
     * Deletes a user from the database based on id
     * </p>
     *
     * @param id Refers the id of the user to delete
     * @return Returns true if the user was successfully deleted, otherwise false
     */
    public boolean delete(final Long id) {
        final String sql = "delete from users where id = ?";

        try (final Connection connection = DatabaseAccessConnection.getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            connection.setAutoCommit(false);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            connection.commit();
            DatabaseAccessConnection.releaseConnection(connection);

            return true;
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return false;
    }

    /**
     * <p>
     * Updates the details of a user in the database
     * </p>
     *
     * @param user Refers {@link User}user object containing the updated user information.
     * @return true if the user information is successfully updated, false otherwise.
     */
    public boolean update(final User user) {
        final String sql = "update users set name = ?, phonenumber = ?, email = ?, password = ? where id = ?";

        try (final Connection connection = DatabaseAccessConnection.getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            connection.setAutoCommit(false);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getMobileNumber());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setLong(5, user.getId());
            preparedStatement.executeUpdate();
            connection.commit();
            DatabaseAccessConnection.releaseConnection(connection);

            return true;
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return false;
    }
}
