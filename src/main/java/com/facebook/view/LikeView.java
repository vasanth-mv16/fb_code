package com.facebook.view;

import com.facebook.controller.LikeController;
import com.facebook.model.Like;
import com.facebook.view.validation.LikeValidation;
import com.facebook.view.validation.UserValidation;

/**
 * <p>
 * Represents the like for the post by user
 * </p>
 *
 * @author vasanth
 * @version 1.0
 */
public class LikeView extends CommonView {

    private static LikeView likeView;
    private final LikeController likeController;
    private final UserView userView;
    private final PostView postView;
    private final UserValidation userValidation;
    private final LikeValidation likeValidation;
    private static Long id = 1L;

    /**
     * <p>
     * Enables the creation of only one object at a time
     * </p>
     */
    private LikeView() {
        likeController = LikeController.getInstance();
        userView = UserView.getInstance();
        postView = PostView.getInstance();
        userValidation = UserValidation.getInstance();
        likeValidation = LikeValidation.getInstance();
    }

    /**
     * <p>
     * Gets the instance of the like view
     * </p>
     *
     * @return Returns the singleton instance of the like view class.
     */
    public static LikeView getInstance() {
        if (null == likeView) {
            likeView = new LikeView();
        }

        return likeView;
    }

    /**
     * <p>
     * Generates the like id of the post
     * </p>
     *
     * @return Returns generated id for the like
     */
    private Long getLikeIdGenerate() {
        return id++;
    }
    /**
     * <p>
     * Gets the like id detail
     * </p>
     *
     * @return Returns the like id of the user
     */
    private Long getLikeIdAndGenerate() {
        try {
            System.out.println("ENTER THE LIKE ID:");
            final Long likeId = Long.valueOf(SCANNER.nextLine());

            if (likeValidation.validateLikeId(String.valueOf(likeId))) {
                return likeId;
            }
        } catch (final NumberFormatException exception) {
            System.out.println("PLEASE ENTER AN INTEGER");
        }

        return getLikeIdAndGenerate();
    }

    /**
     * <p>
     * Gets the user id detail
     * </p>
     *
     * @return Returns the user id of the user
     */
    private Long getUserIdAndGenerate() {
        try {
            System.out.println("ENTER THE USER ID:");
            final Long userId = Long.valueOf(SCANNER.nextLine());

            if (userValidation.validateUserId(String.valueOf(userId))) {
                return userId;
            }
        } catch (final NumberFormatException exception) {
            System.out.println("PLEASE ENTER AN INTEGER");
        }

        return getUserIdAndGenerate();
    }

    /**
     * <p>
     * Shows the menu details for the user to like the post
     * </p>
     *
     * @param userId Refer the user id for the like
     */
    public void displayLikeDetails(final Long userId) {
        System.out.println(String.join("\n", "CLICK 1 TO CREATE LIKE", "CLICK 2 TO GET ALL LIKES",
                "CLICK 3 TO GET LIKE COUNT", "CLICK 4 TO DELETE", "CLICK 5 TO DISPLAY POST DETAILS", "CLICK 6 TO DISPLAY USER OPTIONS"));

        switch (userView.getChoiceAndValidate()) {
            case 1:
                create(userId);
                break;
            case 2:
                get();
                break;
            case 3:
                getCount();
                break;
            case 4:
                delete();
                break;
            case 5:
                postView.displayPostDetails(userId);
                break;
            case 6:
                userView.displaysUserOptions(userId);
                break;
            default:
                System.out.println("INVALID CHOICE, SELECT THE ABOVE CHOICE");
                displayLikeDetails(userId);
        }
        displayLikeDetails(userId);
    }

    /**
     * <p>
     * Shows the like details
     * </p>
     *
     * @param userId Refer the userId for the post comment
     */
    private void create(final Long userId) {
        final Like like = new Like();

        like.setId(getLikeIdGenerate());
        like.setUserId(userId);
        like.setPostId(postView.getPostIdAndValidate());
        System.out.println(likeController.create(like) ? "LIKED" : "NOT LIKED");
    }

    /**
     * <p>
     * Gets the likes by the user
     * </p>
     */
    private void get() {
        System.out.println(likeController.get(getUserIdAndGenerate()));
    }

    /**
     * <p>
     * Gets the likes count for the post
     * </p>
     */
    private void getCount() {
        Long postId = postView.getPostIdAndValidate();
        Long likeCount = likeController.getCount(postId);

        System.out.println("TOTAL LIKE FOR POST ID " + postId + ": " + likeCount);
    }

    /**
     * <p>
     * Unlikes the like for the post
     * </p>
     */
    private void delete() {
        System.out.println(likeController.delete(getLikeIdAndGenerate()) ? "POST UNLIKED" : "LIKE NOT REMOVE");
    }
}
