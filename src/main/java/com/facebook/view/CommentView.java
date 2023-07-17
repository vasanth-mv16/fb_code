package com.facebook.view;

import com.facebook.controller.CommentController;
import com.facebook.customException.InvalidNumberFormat;
import com.facebook.model.Comment;
import com.facebook.view.validation.CommentValidation;

/**
 * <p>
 * Represents the comment for the post by user
 * </p>
 *
 * @author vasanth
 * @version 1.0
 */
public class CommentView extends CommonView {

    private static CommentView commentView;
    private final CommentController commentController;
    private final UserView userView;
    private final PostView postView;
    private final CommentValidation commentValidation;
    private static Long id = 1L;

    /**
     * <p>
     * Enables the creation of only one object at a time
     * </p>
     */
    private CommentView() {
        commentController = CommentController.getInstance();
        userView = UserView.getInstance();
        postView = PostView.getInstance();
        commentValidation = CommentValidation.getInstance();
    }

    /**
     * <p>
     * Gets the instance of the comment view
     * </p>
     *
     * @return Returns the instance of the comment view class
     */
    public static CommentView getInstance() {
        if (null == commentView) {
            commentView = new CommentView();
        }

        return commentView;
    }

    /**
     * <p>
     * Generates the comment id for the post
     * </p>
     *
     * @return Returns the generated id for comment
     */
    private Long getCommentIdGenerate() {
        return id++;
    }

    /**
     * <p>
     * Shows the menu details for the user to comment the post
     * </p>
     *
     * @param userId Refer the user id for the like
     */
    public void displayCommentDetails(final Long userId) {
        System.out.println("CLICK 1 TO CREATE\nCLICK 2 TO DELETE\nCLICK 3 TO DISPLAY POST DETAILS");

        switch (userView.getChoiceAndValidate()) {
            case 1:
                create(userId);
                break;
            case 2:
                delete();
                break;
            case 3:
                postView.displayPostDetails(userId);
                break;
            default:
                System.out.println("INVALID INPUT, SELECT THE ABOVE OPTION");
                displayCommentDetails(userId);
        }
        displayCommentDetails(userId);
    }

    /**
     * <p>
     * Shows the comments details for the post
     * </p>
     *
     * @param userId Refer the userId for the post like
     */
    private void create(final Long userId) {
        final Comment comment = new Comment();

        comment.setId(getCommentIdGenerate());
        comment.setUserId(userId);
        comment.setPostId(postView.getPostIdAndValidate());
        comment.setMessage(getMessage());
        System.out.println(commentController.create(comment) ? "COMMENTED" : "NOT COMMENTED");
    }

    /**
     * <p>
     * Deletes the comment based on comment id
     * </p>
     */
    private void delete() {
        System.out.println(commentController.delete(getCommentIdAndGenerate()) ? "COMMENT DELETED" : "NOT DELETED");
    }

    /**
     * <p>
     * Gets the comment id and validate
     * </p>
     *
     * @return Returns the comment id of the user
     */
    private Long getCommentIdAndGenerate() {
        try {
            System.out.println("ENTER THE COMMENT ID:");
            final Long commentId = Long.valueOf(SCANNER.nextLine());

            if (commentValidation.validateCommentId(String.valueOf(commentId))) {
                return commentId;
            }
        } catch (NumberFormatException exception) {
            throw new InvalidNumberFormat("PLEASE ENTER AN INTEGER");
        }
        return null;
    }

    /**
     * <p>
     * Collects the post caption
     * </p>
     *
     * @return The caption of the post
     */
    private String getMessage() {
        System.out.println("ENTER YOUR MESSAGE FOR YOUR POST:");

        return SCANNER.nextLine().trim();
    }
}
