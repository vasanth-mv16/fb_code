package com.facebook.DAO.Impl;

import com.facebook.DAO.LikeDAO;
import com.facebook.DAOConnection.DatabaseAccessConnection;
import com.facebook.model.Like;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;

/**
 * <p>
 * Provides the Service for the like DAO
 * </p>
 *
 * @author vasanth
 * @version 1.1
 */
public class LikeDAOImpl implements LikeDAO {

    private static LikeDAO likeDAOImpl;

    /**
     * <p>
     * Enables the creation of only one object at a time
     * </p>
     */
    private LikeDAOImpl() {
    }

    /**
     * <p>
     * Gets the instance of like service implementation
     * </p>
     *
     * @return Returns the singleton instance of the like service implementation class.
     */
    public static LikeDAO getInstance() {
        if (null == likeDAOImpl) {
            likeDAOImpl = new LikeDAOImpl();
        }

        return likeDAOImpl;
    }

    /**
     * <p>
     * Creates a like and inserts it into the database
     * </p>
     *
     * @param like Refers the like to create and insert.
     * @return True, if the post was created and inserted successfully, otherwise false
     */
    @Override
    public boolean create(final Like like) {
        final String sql = "insert into likes(user_id, post_id) values (?,?);";

        try (final Connection connection = DatabaseAccessConnection.getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            connection.setAutoCommit(false);
            preparedStatement.setLong(1, like.getUserId());
            preparedStatement.setLong(2, like.getPostId());
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
     * Retrieves all likes with the user id from the database
     * </p>
     *
     * @param userId Refers the user id to retrieve the likes
     * @return Collection of {@link Like} objects with the user id
     */
    @Override
    public Collection<Like> get(final Long userId) {
        final Collection<Like> likes = new ArrayList<>();
        final String sql = "select * from likes where user_id = ?";

        try (final Connection connection = DatabaseAccessConnection.getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            connection.setAutoCommit(false);
            preparedStatement.setLong(1, userId);
            final ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                final Like like = new Like();

                like.setUserId(resultSet.getLong("user_id"));
                like.setPostId(resultSet.getLong("post_id"));
                like.setId(resultSet.getLong("id"));
                likes.add(like);
                connection.commit();
                DatabaseAccessConnection.releaseConnection(connection);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return likes;
    }

    /**
     * <p>
     * Gets the like count of the post id
     * </p>
     *
     * @param postId Represents the user id to get the like count
     * @return Returns count of likes for the post.
     */
    @Override
    public Long getCount(final Long postId) {
        final String sql = "select post_id, count(post_id) as post_count from likes where post_id = ? group by post_id;";

        try (final Connection connection = DatabaseAccessConnection.getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            connection.setAutoCommit(false);
            preparedStatement.setLong(1, postId);
            final ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                final Like like = new Like();

                like.setPostId(resultSet.getLong("post_id"));
            }
            connection.commit();
            DatabaseAccessConnection.releaseConnection(connection);
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return postId;
    }

    /**
     * <p>
     * Unlikes the post by passing like id
     * </p>
     *
     * @param likeId Refers the id for unlike the post
     * @return True, if the post was unliked, otherwise false
     */
    @Override
    public boolean delete(final Long likeId) {
        final String sql = "delete from likes where id = ?";

        try (final Connection connection = DatabaseAccessConnection.getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            connection.setAutoCommit(false);
            preparedStatement.setLong(1, likeId);
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
