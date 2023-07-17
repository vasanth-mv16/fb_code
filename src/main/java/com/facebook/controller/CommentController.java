package com.facebook.controller;

import com.facebook.model.Comment;
import com.facebook.service.CommentService;
import com.facebook.service.Impl2.CommentServiceImpl;

/**
 * <p>
 * Given comment controller acts as request and respond
 * </p>
 *
 * @author vasanth
 * @version 1.0
 */
public class CommentController {

    private static CommentController commentController;
    private static final CommentService COMMENT_DAO = new CommentServiceImpl();

    /**
     * <p>
     * Enables the creation of only one object at a time
     * </p>
     */
    private CommentController() {
    }

    /**
     * <p>
     * Gets the instance of the comment controller
     * </p>
     *
     * @return Returns the instance of the comment controller
     */
    public static CommentController getInstance() {
        if (null == commentController) {
            commentController = new CommentController();
        }
        return commentController;
    }

    /**
     * <p>
     * Checks the comment to be created
     * </p>
     *
     * @param comment Refers {@link Comment} to created
     * @return True if the comment is created, false otherwise
     */
    public boolean create(final Comment comment) {
        return COMMENT_DAO.create(comment);
    }

    /**
     * <p>
     * Checks the comment to be deleted
     * </p>
     *
     * @param id Refers the id to delete the comment
     * @return True if the comment is deleted, false otherwise
     */
    public boolean delete(final Long id) {
        return COMMENT_DAO.delete(id);
    }
}
