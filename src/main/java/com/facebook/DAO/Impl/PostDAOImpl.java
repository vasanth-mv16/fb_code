package com.facebook.DAO.Impl;

import com.facebook.DAO.PostDAO;
import com.facebook.DAOConnection.DatabaseAccessConnection;
import com.facebook.model.Post;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;

/**
 * <p>
 * Provides the following services for post
 * </p>
 *
 * @author vasanth
 * @version 1.1
 */
public class PostDAOImpl implements PostDAO {

    private static PostDAO postDAOImpl;

    /**
     * <p>
     * Enables the creation of only one object at a time
     * </p>
     */
    private PostDAOImpl() {
    }

    /**
     * <p>
     * Gets the instance of post service implementation
     * </p>
     *
     * @return Returns the singleton instance of the post service implementation class.
     */
    public static PostDAO getInstance() {
        if (null == postDAOImpl) {
            postDAOImpl = new PostDAOImpl();
        }

        return postDAOImpl;
    }

    /**
     * <p>
     * Creates a new post and inserts it into the database
     * </p>
     *
     * @param posts The post to create and insert.
     * @return True, if the post was created and inserted successfully, otherwise false
     */
    @Override
    public boolean create(final Post posts) {
        final String sql = "insert into posts(user_id, caption, location, uploadtime) values (?,?,?,?);";

        try (final Connection connection = DatabaseAccessConnection.getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            connection.setAutoCommit(false);
            preparedStatement.setLong(1, posts.getUserId());
            preparedStatement.setString(2, posts.getCaption());
            preparedStatement.setString(3, posts.getLocation());
            preparedStatement.setTimestamp(4, posts.getUploadTime());
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
     * Retrieves all posts with the user id from the database
     * </p>
     *
     * @param user_id Refers the user id to retrieve the posts
     * @return A collection of {@link Post} objects with the user id
     */
    @Override
    public Collection<Post> getAll(final Long user_id) {
        final Collection<Post> POSTS = new ArrayList<>();
        final String sql = "select * from posts where user_id = ?";

        try (final Connection connection = DatabaseAccessConnection.getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            connection.setAutoCommit(false);
            preparedStatement.setLong(1, user_id);
            final ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                final Post post = new Post();

                post.setUserId(resultSet.getLong("user_id"));
                post.setId(resultSet.getLong("id"));
                post.setCaption(resultSet.getString("caption"));
                post.setLocation(resultSet.getString("location"));
                post.setUploadTime(resultSet.getTimestamp("uploadtime"));
                POSTS.add(post);
            }
            connection.commit();
            DatabaseAccessConnection.releaseConnection(connection);
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return POSTS;
    }

    /**
     * <p>
     * Retrieves posts with the post id from the database
     * </p>
     *
     * @param id Refers the id of the user to retrieve the posts
     * @return A collection of {@link Post} objects with the user id
     */
    @Override
    public Post get(final Long id) {
        final String sql = "select * from posts where id = ?";

        try (final Connection connection = DatabaseAccessConnection.getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            connection.setAutoCommit(false);
            preparedStatement.setLong(1, id);
            final ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                final Post post = new Post();

                post.setUserId(resultSet.getLong("user_id"));
                post.setId(resultSet.getLong("id"));
                post.setCaption(resultSet.getString("caption"));
                post.setLocation(resultSet.getString("location"));
                post.setUploadTime(resultSet.getTimestamp("uploadtime"));
                connection.commit();
                DatabaseAccessConnection.releaseConnection(connection);

                return post;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return null;
    }

    /**
     * <p>
     * Updates the caption and location of a post in the database
     * </p>
     *
     * @param post Refers the post for update caption and location
     * @return True if the post was updated successfully, otherwise false
     */
    @Override
    public boolean update(final Post post) {
        final String sql = "update posts set caption = ?, location = ? where id = ? AND user_id = ?";

        try (final Connection connection = DatabaseAccessConnection.getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            connection.setAutoCommit(false);
            preparedStatement.setString(1, post.getCaption());
            preparedStatement.setString(2, post.getLocation());
            preparedStatement.setLong(3, post.getId());
            preparedStatement.setLong(4, post.getUserId());
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
     * Deletes a post from the database based on the post id
     * </p>
     *
     * @param id Refer the id of the post to delete
     * @return True, if the post was deleted successfully, otherwise false
     */
    @Override
    public boolean delete(final Long id) {
        final String sql = "delete from posts where id = ?";

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
}
