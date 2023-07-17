package com.facebook.view;

import com.facebook.customException.InvalidNumberFormat;
import com.facebook.model.User;
import com.facebook.model.UserBuilder;

/**
 * <p>
 * Handles the process of updating, deleting, and retrieving user information
 * </p>
 *
 * @author vasanth
 * @version 1.0
 */
public class UserView extends CommonView {
    private static final AuthenticationView authenticationView = AuthenticationView.getInstance();
    private static UserView userView;
    private static Long id = 1L;

    /**
     * <p>
     * Enables the creation of only one object at a time
     * </p>
     */
    private UserView() {
    }

    /**
     * <p>
     * Gets the instance of the user view
     * </p>
     *
     * @return Returns the instance of the view
     */
    public static UserView getInstance() {
        if (null == userView) {
            userView = new UserView();
        }

        return userView;
    }

    /**
     * <p>
     * Creates a unique id for the user
     * </p>
     *
     * @return Returns the id of the user
     */
    public Long getUserIdGenerate() {
        return id++;
    }

    /**
     * <p>
     * Collects and validates the user's email address
     * </p>
     *
     * @return Returns validated email
     */
    public String getEmailAndValidate() {
        System.out.println("ENTER YOUR EMAIL ID : \nDO YOU WANT GO MENU, PRESS($)");
        final String email = SCANNER.nextLine().trim();

        if (email.contains("$")) {
            authenticationView.displayMenu();
        }

        if (userValidation.validateEmail(email)) {
            return email;
        } else {
            System.out.println(String.join("", "EMAIL CONTAINS ONLY LETTERS 'A-Z', 'a-z', " +
                    "NUMBERS '0-9', DOT '.' AND '@', AFTER 'a-z' AND DOT '.' THEN 2 OR MORE 'COM','ORG', 'EDU', 'IN'"));

            return getEmailAndValidate();
        }
    }

    /**
     * <p>
     * Collects and validates the user's mobile number
     * </p>
     *
     * @return Returns validated mobile number
     */
    public String getMobileNumberAndValidate() {
        System.out.println("ENTER YOUR MOBILE NUMBER : \nDO YOU WANT GO MENU, PRESS($)");
        final String mobileNumber = SCANNER.nextLine().trim();

        if (mobileNumber.contains("$")) {
            authenticationView.displayMenu();
        }

        if (userValidation.validateMobileNumber(mobileNumber)) {
            return mobileNumber;
        } else {
            System.out.println(String.join("", "MOBILE NUMBER MUST CONTAINS '+91' FOLLOWED BY ",
                    "10 DIGITS AND STARTS, WITH RANGE (6-9)"));

            return getMobileNumberAndValidate();
        }
    }

    /**
     * <p>
     * Collects and validates the user's name
     * </p>
     *
     * @return Returns validated name
     */
    public String getNameAndValidate() {
        System.out.println("ENTER YOUR USERNAME :\nDO YOU WANT TO GO MENU, PRESS '$'");
        final String name = SCANNER.nextLine().trim();

        if (name.contains("$")) {
            authenticationView.displayMenu();
        }

        if (userValidation.validateName(name)) {
            return name;
        } else {
            System.out.println("USERNAME MUST CONTAIN 'a-z' AND 'A-Z'");

            return getNameAndValidate();
        }
    }

    /**
     * <p>
     * Collects and validates the user's password
     * </p>
     *
     * @return Returns validated password
     */
    public String getPasswordAndValidate() {
        System.out.println("ENTER PASSWORD : \nDO YOU WANT GO MENU, PRESS($)");
        final String password = SCANNER.nextLine().trim();

        if (password.contains("$")) {
            authenticationView.displayMenu();
        }

        if (userValidation.validatePassword(password)) {
            return password;
        } else {
            System.out.println(String.join("", "PASSWORD MUST CONTAINS AT LEAST ONE CAPITAL AND SMALL ",
                    "LETTER, NUMBER, SPECIAL CHARACTER"));

            return getPasswordAndValidate();
        }
    }

    /**
     * <p>
     * Collects and validates the user's date of birth
     * </p>
     *
     * @return Returns validated date of birth
     */
    public String getDateOfBirthAndValidate() {
        System.out.println("ENTER DATE OF BIRTH : \nDO YOU WANT GO MENU, PRESS($)");
        final String dateOfBirth = SCANNER.nextLine().trim();

        if (dateOfBirth.contains("$")) {
            authenticationView.displayMenu();
        }

        if (userValidation.validateDateOfBirth(dateOfBirth)) {
            return dateOfBirth;
        } else {
            System.out.println("ENTER THE CORRECT DATE!!!");

            return getDateOfBirthAndValidate();
        }
    }

    /**
     * <p>
     * Collects and validates the user's gender
     * </p>
     *
     * @return Returns {@link User.Gender} of the user
     */
    public User.Gender getGenderAndValidate() {
        System.out.println("CLICK 1 TO MALE\nCLICK 2 TO FEMALE\nCLICK 3 TO OTHERS");
        final User.Gender gender = User.Gender.setUserGender(getChoiceAndValidate());

        if (null != gender) {
            return gender;
        } else {
            System.out.println("INVALID USER GENDER, PLEASE SELECT THE ABOVE CHOICES");

            return getGenderAndValidate();
        }
    }

    /**
     * <p>
     * Collects and validates the user's choice as an integer value
     * </p>
     *
     * @return Returns validated choice
     */
    public int getChoiceAndValidate() {
        try {
            System.out.println("ENTER YOUR CHOICE :");
            final int choice = Integer.parseInt(SCANNER.nextLine());

            if (userValidation.validateChoice(String.valueOf(choice))) {
                return choice;
            }
        } catch (NumberFormatException exception) {
            throw new InvalidNumberFormat("PLEASE ENTER AN INTEGER");
        }

        return getChoiceAndValidate();
    }

    /**
     * <p>
     * Gets the user id and validate
     * </p>
     *
     * @return Returns the user id of the user
     */
    private Long getIdAndValidate() {
        try {
            System.out.println("ENTER THE USER ID:");
            final Long userId = Long.valueOf(SCANNER.nextLine());

            if (userValidation.validateUserId(String.valueOf(userId))) {
                return userId;
            }
        } catch (NumberFormatException exception) {
            throw new InvalidNumberFormat("PLEASE ENTER AN INTEGER");
        }

        return getIdAndValidate();
    }

    /**
     * <p>
     * Retrieves and returns a user's id
     * </p>
     *
     * @param id The id to retrieves the user
     * @return Returns {@link User} details
     */
    private User getUserId(final Long id) {
        System.out.println(userController.get(id));

        return userController.get(id);
    }

    /**
     * <p>
     * Displays the user options and performs the action based on the user's choice.
     * </p>
     *
     * @param id The user id to edit, delete, retrieves
     */
    public void displaysUserOptions(final Long id) {
        final PostView postView = PostView.getInstance();

        System.out.println(String.join("\n", "CLICK 1 TO UPDATE", "CLICK 2 TO DELETE",
                "CLICK 3 TO GET USERS", "CLICK 4 TO DISPLAY POST DETAILS", "CLICK 5 TO LOGOUT", "CLICK 6 TO EXIT"));

        switch (getChoiceAndValidate()) {
            case 1:
                update(id);
                break;
            case 2:
                delete();
                break;
            case 3:
                get();
                break;
            case 4:
                postView.displayPostDetails(id);
                break;
            case 5:
                logout();
                break;
            case 6:
                System.out.println("EXITING");
                SCANNER.close();
                System.exit(0);
                break;
            default:
                System.out.println("INVALID CHOICE, SELECT THE ABOVE CHOICE");
                displaysUserOptions(id);
        }
        displaysUserOptions(id);
    }

    /**
     * <p>
     * Updates the user's account information based on the provided id
     * </p>
     *
     * @param id The user id to update
     */
    private void update(final Long id) {
        final UserBuilder user = new UserBuilder();
        final User existingUser = getUserId(id);

        user.setId(id);
        System.out.println("DO YOU WANT TO EDIT NAME, PRESS 'YES' OR 'Y' AND DON'T WANT TO EDIT PRESS ANY KEY ");
        user.setName(userValidation.validateAccess(SCANNER.nextLine()) ? getNameAndValidate() : existingUser.getName());
        System.out.println("DO YOU WANT TO EDIT NAME, PRESS 'YES' OR 'Y' AND DON'T WANT TO EDIT PRESS ANY KEY ");
        user.setEmail(userValidation.validateAccess(SCANNER.nextLine()) ? getEmailAndValidate() : existingUser.getEmail());
        System.out.println("DO YOU WANT TO EDIT NAME, PRESS 'YES' OR 'Y' AND DON'T WANT TO EDIT PRESS ANY KEY ");
        user.setPassword(userValidation.validateAccess(SCANNER.nextLine()) ? getPasswordAndValidate() : existingUser.getPassword());
        System.out.println("DO YOU WANT TO EDIT NAME, PRESS 'YES' OR 'Y' AND DON'T WANT TO EDIT PRESS ANY KEY ");
        user.setMobileNumber(userValidation.validateAccess(SCANNER.nextLine()) ? getMobileNumberAndValidate() : existingUser.getMobileNumber());
        System.out.println(userController.update(user.build()) ? "Account Updated Successfully" : "Not Successful");
    }

    /**
     * <p>
     * Retrieves and returns a user object based on the provided user id
     * </p>
     *
     * @return Returns {@link User} details
     */
    private User get() {
        final User user = (userController.get(getIdAndValidate()));

        System.out.println(user);
        return user;
    }

    /**
     * <p>
     * Deletes a user based on the provided userId
     * </p>
     */
    private void delete() {
        if (userController.delete(getIdAndValidate())) {
            System.out.println("SUCCESSFULLY DELETED");
            authenticationView.displayMenu();
        } else {
            System.out.println("NOT DELETED");
        }
    }

    /**
     * <p>
     * Handles the user logout process, displaying a confirmation message, and log out the user if confirmed
     * </p>
     */
    private void logout() {
        System.out.println("DO YOU WANT TO LOGOUT?,CLICK YES AND OTHERWISE CLICK ANY KEY FOR MENU OPTIONS");

        if (userValidation.validateAccess(SCANNER.nextLine())) {
            authenticationView.displayMenu();
        }
    }
}