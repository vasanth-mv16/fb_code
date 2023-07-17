package com.facebook.view.validation;

/**
 * <p>
 * Given class used for validation the post details
 * </p>
 *
 * @author vasanth
 * @version 1.0
 */
public class PostValidation {

    private static PostValidation postValidation;

    /**
     * <p>
     * Enables the creation of only one object at a time
     * </p>
     */
    private PostValidation() {
    }

    /**
     * <p>
     * Gets the instance of the post validation
     * </p>
     *
     * @return Returns the instance of the post validation class
     */
    public static PostValidation getInstance() {
        if (null == postValidation) {
            postValidation = new PostValidation();
        }

        return postValidation;
    }

    /**
     * <p>
     * Validates a check string using a regular expression pattern
     * </p>
     *
     * @param access The access to be validated
     * @return True if the check is valid, false otherwise
     */
    public boolean validateAccess(final String access) {
        return (access.equalsIgnoreCase("yes") || access.equalsIgnoreCase("y"));
    }

    /**
     * <p>
     * Validates a post id string using a regular expression pattern
     * </p>
     *
     * @param postId The post id to be validated
     * @return True if the user id is valid, false otherwise
     */
    public boolean validatePostId(final String postId) {
        return postId.matches("[\\d]");
    }
}
