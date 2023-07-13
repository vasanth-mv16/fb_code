package com.facebook.view;

import com.facebook.controller.UserController;
import com.facebook.view.validation.UserValidation;

import java.util.Scanner;

/**
 * <p>
 * Manages the view for user, post, like, comment for getting user input
 * </p>
 *
 * @author vasanth
 * @version 1.0
 */
public class CommonView {

    public static final Scanner SCANNER = new Scanner(System.in);
    public final UserController userController = UserController.getInstance();
    public final UserValidation userValidation = UserValidation.getInstance();
    private static CommonView commonView;

    /**
     * <p>
     * Enables the creation of only one object at a time
     * </p>
     */
    public CommonView() {
    }

    /**
     * <p>
     * Gets the instance of the common view
     * </p>
     *
     * @return Returns the singleton instance of the common view class
     */
    public static CommonView getInstance() {
        if (null == commonView) {
            commonView = new CommonView();
        }

        return commonView;
    }
}
