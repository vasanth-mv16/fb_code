package com.facebook.model;

/**
 * <p>
 * Represents the post comment by the user
 * </p>
 *
 * @author vasanth
 * @version 1.0
 */
public class Comment {

    private Long id ;
    private Long userId;
    private Long postId;
    private String message;

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

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public String toString() {
        return String.format("COMMENT ID =%d USER ID =%d POST ID =%d  MESSAGE =%s", id, userId,postId,message);
    }
}
