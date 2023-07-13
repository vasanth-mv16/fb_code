package com.facebook.DAO.Impl;

import com.facebook.DAO.AuthenticationDAO;
import com.facebook.DAOConnection.JDBCConnection;
import com.facebook.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * <p>
 * Provides the service for Authentication DAO
 * </p>
 *
 * @author vasanth
 * @version 1.1
 */
public class AuthenticationDAOImpl implements AuthenticationDAO {

    private static AuthenticationDAOImpl authenticationDAOImpl;

    /**
     * <p>
     * Enables the creation of only one object at a time
     * </p>
     */
    private AuthenticationDAOImpl() {
    }

    public static AuthenticationDAOImpl getInstance() {
        if(null == authenticationDAOImpl) {
            authenticationDAOImpl = new AuthenticationDAOImpl();
        }

        return authenticationDAOImpl;
    }

    /**
     * <p>
     * Signs up a new user by inserting their details into the user table
     * </p>
     *
     * @param user Represents the user details for getting information
     * @return true if the sign-up was successful, false otherwise.
     */
    public boolean signUp(final User user)  {
        final String sql = "insert into users(name, phonenumber, password, email, gender, dateofbirth ) values(?, ?, ?, ?, ?::gender, ?)";

        try (final Connection connection = JDBCConnection.getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            connection.setAutoCommit(false);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getMobileNumber());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getGender().toString());
            preparedStatement.setString(6, user.getDateOfBirth());
            preparedStatement.executeUpdate();
            connection.commit();

            return true;
        } catch (final Exception exception) {
            exception.printStackTrace();
        }

        return false;
    }

    /**
     * <p>
     * Signs in a user by checking email or mobile number
     * </p>
     *
     * @param user Refers {@link User}user to sign in
     * @return true if the user is successfully signed in, otherwise false
     */
    @Override
    public boolean signIn(final User user) {
        return (null != user.getEmail() ? isUserEmailExists(user) : isUserMobileNumberExists(user));
    }

    /**
     * <p>
     * Checks the users mobile number and password exists in the database
     * </p>
     *
     * @param user {@link User}user object containing the mobile number and password.
     * @return true if a user with the given mobile number and password is found, otherwise false
     */
    public boolean isUserMobileNumberExists(final User user) {
        final String sql = "select * from users where phonenumber = ? and password = ?;";

        try (final Connection connection = JDBCConnection.getConnection();
                final PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            connection.setAutoCommit(false);
            preparedStatement.setString(1, user.getMobileNumber());
            preparedStatement.setString(2, user.getPassword());
            final ResultSet resultSet = preparedStatement.executeQuery();

            connection.commit();
            JDBCConnection.releaseConnection(connection);

            return resultSet.next();
        } catch (final Exception exception) {
            exception.printStackTrace();
        }

        return false;
    }

    /**
     * <p>
     * Checks the users email and password exists in the database
     * </p>
     *
     * @param user {@link User}user object containing the mobile number and password.
     * @return true if a user with the given mobile number and password is found, otherwise false
     */
    public boolean isUserEmailExists(final User user) {
        final String sql = "select * from users where email = ? and password = ?";

        try (final Connection connection = JDBCConnection.getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            connection.setAutoCommit(false);

            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            final ResultSet resultSet = preparedStatement.executeQuery();

            connection.commit();
            JDBCConnection.releaseConnection(connection);

            return resultSet.next();
        } catch (final Exception exception) {
            exception.printStackTrace();
        }

        return false;
    }

    /**
     * <p>
     * Retrieves the id of the user based on mobile number and email
     * </p>
     *
     * @param user {@link User}user object containing the mobile number and email address
     * @return Returns the id of the user if found, otherwise null
     */
    @Override
    public Long getUserId(final User user) {
        final String sql = "select id from users where phonenumber = ? or email = ?";

        try (final Connection connection = JDBCConnection.getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            connection.setAutoCommit(false);

            preparedStatement.setString(1, user.getMobileNumber());
            preparedStatement.setString(2, user.getEmail());
            final ResultSet resultSet = preparedStatement.executeQuery();

            connection.commit();
            JDBCConnection.releaseConnection(connection);

            if (resultSet.next()) {
                return resultSet.getLong("id");
            }
        } catch (final Exception exception) {
            exception.printStackTrace();
        }

        return null;
    }
}
