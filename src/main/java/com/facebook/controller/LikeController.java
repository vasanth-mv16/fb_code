package com.facebook.controller;

import com.facebook.model.Like;
import com.facebook.service.Impl2.LikeServiceImpl;
import com.facebook.service.LikeService;

import java.util.Collection;

/**
 * <p>
 * Given controller acts as request and respond
 * </p>
 *
 * @author vasanth
 * @version 1.0
 */
public class LikeController {

    private static LikeController likeController;
    private static final LikeService LIKE_DAO_IMPL = new LikeServiceImpl();

    /**
     * <p>
     * Enables the creation of only one object at a time
     * </p>
     */
    private LikeController() {
    }

    /**
     * <p>
     * Gets the instance of the like controller
     * </p>
     *
     * @return Returns the instance of the like controller
     */
    public static LikeController getInstance() {
        if (null == likeController) {
            likeController = new LikeController();
        }

        return likeController;
    }

    /**
     * <p>
     * Checks the like to be created
     * </p>
     *
     * @param like Refers {@link Like} to created
     * @return True if the like is created, false otherwise
     */
    public boolean create(final Like like) {
        return LIKE_DAO_IMPL.create(like);
    }

    /**
     * <p>
     * Gets the like details
     * </p>
     *
     * @return Collection of like of the post
     */
    public Collection<Like> get(final Long userId) {
        return LIKE_DAO_IMPL.get(userId);
    }

    /**
     * <p>
     * Gets the like count details
     * </p>
     *
     * @param postId Represents the user id has to get the post
     * @return returns the like count of the post
     */
    public Long getCount(final Long postId) {
        return LIKE_DAO_IMPL.getCount(postId);
    }

    /**
     * <p>
     * Checks the like to be deleted
     * </p>
     *
     * @param likeId Refers the id to unlike the post
     * @return True if the like is deleted, false otherwise
     */
    public boolean delete(final Long likeId) {
        return LIKE_DAO_IMPL.delete(likeId);
    }
}
