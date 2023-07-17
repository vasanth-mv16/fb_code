package com.facebook.service;

import com.facebook.model.Post;

import java.util.Collection;

/**
 * <p>
 * Provides service for the post
 * </p>
 *
 * @author vasanth
 * @version 1.0
 */
public interface PostService {

    /**
     * <p>
     * Checks if the post details are created, and adds the post
     * </p>
     *
     * @param post Refers {@link Post} to add
     * @return True if the post is successfully added, false otherwise
     */
    boolean create(final Post post);

    /**
     * <p>
     * Retrieves the collection of post details
     * </p>
     *
     * @return The collection of posts
     */
    Collection<Post> getAll(final Long userId);

    /**
     * <p>
     * Retrieves the post details using the post id
     * </p>
     *
     * @param id Represents the id of the post to retrieve
     * @return Returns {@link Post} details of the user
     */
    Post get(final Long id);

    /**
     * <p>
     * Checks if the post details are updated
     * </p>
     *
     * @param post Refers {@link Post} to update
     * @return True if the post is successfully updated, false otherwise
     */
    boolean update(final Post post);

    /**
     * <p>
     * Deletes the post details by passing id
     * </p>
     *
     * @param id Refers the id for delete the post
     * @return True if the post is successfully updated, false otherwise
     */
    boolean delete(final Long id);
}
