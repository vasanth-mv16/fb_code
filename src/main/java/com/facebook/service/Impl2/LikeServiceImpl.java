package com.facebook.service.Impl2;

import com.facebook.DAO.Impl.LikeDAOImpl;
import com.facebook.DAO.LikeDAO;
import com.facebook.model.Like;
import com.facebook.service.LikeService;

import java.util.Collection;

/**
 * <p>
 * Implements the following services for the like
 * </p>
 *
 * @author vasanth
 * @version 1.0
 */
public class LikeServiceImpl implements LikeService {

    private  final LikeDAO LIKE_DAO_IMPL = LikeDAOImpl.getInstance();

    /**
     * <p>
     * Checks the like is created
     * </p>
     *
     * @param like Refer {@link Like} to create
     * @return true, if the like is created, otherwise false
     */
    @Override
    public boolean create(final Like like) {
        return LIKE_DAO_IMPL.create(like);
    }

    /**
     * <p>
     * Gets the like list details
     * </p>
     *
     * @param userId Refers the userId to get like
     * @return Collection of likes
     */
    @Override
    public Collection<Like> get(final Long userId) {
        return LIKE_DAO_IMPL.get(userId);
    }

    /**
     * <p>
     * Gets the like count for the user
     * </p>
     *
     * @param postId Represents the user id to get the like count
     * @return Gets the like count of the user
     */
    @Override
    public Long getCount(final Long postId) {
        return LIKE_DAO_IMPL.getCount(postId);
    }

    /**
     * <p>
     * Deletes the like for post by the id
     * </p>
     *
     * @param likeId Refers the id for unlike the post
     * @return true, if the like is unliked, otherwise false
     */
    @Override
    public boolean delete(final Long likeId) {
        return LIKE_DAO_IMPL.delete(likeId);
    }
}
