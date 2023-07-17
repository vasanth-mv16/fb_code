package com.facebook.model;

/**
 * <p>
 * Represents the post like by user
 * </p>
 *
 * @version 1.0
 * @author vasanth
 */
public class Like {

    private Long id;
    private Long userId;
    private Long postId;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(final Long userId) {
        this.userId = userId;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(final Long postId) {
        this.postId = postId;
    }

    public String toString() {
        return String.format("LIKE ID =%d USER ID =%d POST ID =%d", id, userId,postId);
    }
}
