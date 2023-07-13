package com.facebook.service.Impl;

import com.facebook.model.Like;
import com.facebook.service.LikeService;

import java.util.ArrayList;
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

    private static LikeService likeServiceImpl;
    private static final Collection<Like> LIKE_LIST = new ArrayList<>();

    /**
     * <p>
     * Default constructor for like service implementation
     * </p>
     */
    private LikeServiceImpl() {
    }

    /**
     * <p>
     * Gets the instance of like service implementation
     * </p>
     *
     * @return Returns the singleton instance of the like service implementation class
     */
    public static LikeService getInstance() {
        if (null == likeServiceImpl) {
            likeServiceImpl = new LikeServiceImpl();
        }

        return likeServiceImpl;
    }

    /**
     * {@inheritDoc}
     *
     * @param like Refers {@link Like} to be create
     * @return boolean - True if the like is created, false otherwise
     */
    public boolean create(final Like like) {
        return LIKE_LIST.add(like);
    }

    /**
     * {@inheritDoc}
     *
     * @param userId Refers the userId to get like
     * @return Collection of likes
     */
    public Collection<Like> get(final Long userId) {
        return LIKE_LIST;
    }

    /**
     * {@inheritDoc}
     *
     * @param postId Represents the post id to get the like count
     * @return Gets the like count of the user
     */
    public Long getCount(final Long postId) {
        Long likeCount = 0L;

        for (final Like like : LIKE_LIST) {

            if (like.getPostId().equals(postId)) {
                likeCount++;
            }
        }

        return likeCount;
    }

    @Override
    public boolean delete(Long likeId) {
//        final Like like = get(likeId);
//
//        if (null != like) {
//            return LIKE_LIST.remove(like);
//        }
       return false;
    }
}
