package com.facebook.view;

import com.facebook.controller.PostController;
import com.facebook.customException.InvalidNumberFormat;
import com.facebook.model.Post;
import com.facebook.model.PostBuilder;
import com.facebook.view.validation.PostValidation;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Collection;

/**
 * <p>
 * Manages the view for posts, including creating, retrieving, updating, and printing post details.
 * </p>
 *
 * @author vasanth
 * @version 1.0
 */
public class PostView extends CommonView {

    private final UserView userView;
    private final LikeView likeView;
    private final CommentView commentView;
    private final PostController postController;
    private final PostValidation postValidation;
    private static PostView postView;
    private static Long id = 1L;

    /**
     * <p>
     * Enables the creation of only one object at a time
     * </p>
     */
    private PostView() {
        userView = UserView.getInstance();
        likeView = LikeView.getInstance();
        commentView = CommentView.getInstance();
        postController = PostController.getInstance();
        postValidation = PostValidation.getInstance();

    }

    /**
     * <p>
     * Gets the instance of the post view
     * </p>
     *
     * @return Returns the singleton instance of the post view class.
     */
    public static PostView getInstance() {
        if (null == postView) {
            postView = new PostView();
        }

        return postView;
    }

    /**
     * <p>
     * Gets the post id and validate
     * </p>
     *
     * @return Returns the post id of the user
     */
    public Long getPostIdAndValidate() {
        try {
            System.out.println("ENTER THE POST ID:");
            final Long postId = Long.valueOf(SCANNER.nextLine());

            if (postValidation.validatePostId(String.valueOf(postId))) {
                return postId;
            }
        } catch (NumberFormatException exception) {
            throw new InvalidNumberFormat("PLEASE ENTER AN INTEGER");
        }

        return getPostIdAndValidate();
    }

    /**
     * <p>
     * Collects the post caption
     * </p>
     *
     * @return The caption of the post
     */
    private String getCaption() {
        System.out.println("ENTER YOUR CAPTION FOR YOUR POST:");

        return SCANNER.nextLine().trim();
    }

    /**
     * <p>
     * Collects the post location
     * </p>
     *
     * @return Tha location of the post
     */
    private String getLocation() {
        System.out.println("ENTER YOUR LOCATION FOR YOUR POST:");

        return SCANNER.nextLine().trim();
    }

    /**
     * <p>
     * Shows the menu details for the user to post, like, comment and edit
     * </p>
     *
     * @param userId Refer the user id for the post
     */
    public void displayPostDetails(final Long userId) {
        System.out.println(String.join("\n", "CLICK 1 TO CREATE", "CLICK 2 TO GET", "CLICK 3 TO GET USING ID",
                "CLICK 4 TO DELETE", "CLICK 5 TO UPDATE", "CLICK 6 TO DISPLAY LIKE DETAILS", "CLICK 7 TO DISPLAY COMMENT OPTIONS",
                "CLICK 8 TO DISPLAY USER OPTION"));

        switch (userView.getChoiceAndValidate()) {
            case 1:
                create(userId);
                break;
            case 2:
                getAll(userId);
                break;
            case 3:
                get();
                break;
            case 4:
                delete();
                break;
            case 5:
                update();
                break;
            case 6:
                likeView.displayLikeDetails(userId);
                break;
            case 7:
                commentView.displayCommentDetails(userId);
                break;
            case 8:
                userView.displaysUserOptions(userId);
                break;
            default:
                System.out.println("INVALID CHOICE,SELECT THE ABOVE CHOICE");
                displayPostDetails(userId);
        }
        displayPostDetails(userId);
    }

    /**
     * <p>
     * Creates a new post with the user, caption, and location
     * </p>
     *
     * @param userId Refer the user id for the post
     */
    private void create(final Long userId) {
        final PostBuilder post = new PostBuilder();
        final Timestamp postUploadTime = Timestamp.from(Instant.now());

        post.setUserId(userId);
        post.setId(getPostIdGenerate());
        post.setCaption(getCaption());
        post.setLocation(getLocation());
        post.setUploadTime(postUploadTime);

        if (postController.create(post.bulidPost())) {
            System.out.println("SUCCESSFULLY POSTED");
        } else {
            System.out.println("FAILED TO POST");
            create(userId);
        }
    }

    /**
     * <p>
     * Generates id for the post
     * </p>
     *
     * @return Returns the id of the post
     */
    private Long getPostIdGenerate() {
        return id++;
    }

    /**
     * <p>
     * Retrieves and prints the details of the post
     * </p>
     *
     * @return Collection of post
     */
    private Collection<Post> getAll(final Long userId) {
        System.out.println(postController.getALl(userId));

        return postController.getALl(userId);
    }

    /**
     * <p>
     * Retrieves and returns a User object based on the provided user ID
     * </p>
     *
     * @return Returns {@link Post} of the user
     */
    private Post get() {
        final Post post = postController.get(getPostIdAndValidate());

        System.out.println(post);

        if (null == post) {
            System.out.println("POST NOT FOUND");
        }

        return post;
    }

    /**
     * <p>
     * Updates the post by the user to edit the caption and location
     * </p>
     */
    private void update() {
        final PostBuilder post = new PostBuilder();
        final Post get = get();

        post.setId(get.getId());
        post.setUserId(get.getUserId());
        System.out.println("DO YOU WANT TO EDIT CAPTION, PRESS ANY KEY AND DON'T WANT TO EDIT PRESS 'NO' OR 'N' ");
        post.setCaption((postValidation.validateAccess(SCANNER.nextLine())) ? getCaption() : get.getCaption());
        System.out.println("DO YOU WANT TO EDIT LOCATION, PRESS ANY KEY AND DON'T WANT TO EDIT PRESS 'NO' OR 'N' ");
        post.setLocation((postValidation.validateAccess(SCANNER.nextLine())) ? getLocation() : get.getLocation());

        if (postController.update(post.bulidPost())) {
            System.out.println("POST UPDATED");
        } else {
            System.out.println("NOT UPDATED");
            update();
        }
    }

    /**
     * <p>
     * Deletes the post based on post id
     * </p>
     */
    private void delete() {
        System.out.println(postController.delete(getPostIdAndValidate()) ? "SUCCESSFULLY DELETED" : "NOT DELETED");
    }
}
