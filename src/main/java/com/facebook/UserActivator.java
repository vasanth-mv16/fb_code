package com.facebook;

import com.facebook.view.AuthenticationView;

/**
 * <p>
 * Providing a main Activator for user displaying menu
 * </p>
 *
 * @author vasanth
 * @version 1.0
 */
public class UserActivator {

    /**
     * <p>
     * Main entry of the facebook application
     * </p>
     *
     * @param args Refers command-line arguments to the facebook application
     */
    public static void main(final String[] args) {
        final AuthenticationView authenticationView = AuthenticationView.getInstance();

        authenticationView.displayMenu();
    }
}
