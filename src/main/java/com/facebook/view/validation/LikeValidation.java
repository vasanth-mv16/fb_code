package com.facebook.view.validation;

/**
 * <p>
 * Given class used for validation the like details
 * </p>
 *
 * @author vasanth
 * @version 1.0
 */
public class LikeValidation {

    private static LikeValidation likeValidation;

    /**
     * <p>
     * Enables the creation of only one object at a time
     * </p>
     */
    private LikeValidation() {}

    /**
     * <p>
     * Gets the instance of the like validation
     * </p>
     *
     * @return Returns the instance of the like validation class
     */
    public static LikeValidation getInstance() {
        if(null == likeValidation) {
            likeValidation = new LikeValidation();
        }
        return likeValidation;
    }

    /**
     * <p>
     * Validates a like id string using a regular expression pattern
     * </p>
     *
     * @param likeId The like id to be validated
     * @return True if the like id is valid, false otherwise
     */
    public boolean validateLikeId(final String likeId) {
        return likeId.matches("[\\d]");
    }
}
