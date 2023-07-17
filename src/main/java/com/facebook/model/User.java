package com.facebook.model;

/**
 * <p>
 * Represents the given user for storing information
 * </p>
 *
 * @version 1.0
 * @author vasanth
 */
public class User {

    private  Gender gender;
    private  Long id;
    private  String name;
    private  String mobileNumber;
    private  String password;
    private  String email;
    private  String dateOfBirth;

    /**
     * An enum with values MALE, FEMALE, OTHERS
     */
    public enum Gender {

        male(1), female(2), others(3);
        final int choice;

        Gender(final int choice) {
            this.choice = choice;
        }

        public static Gender setUserGender(final int choice) {
            for (final Gender existingGender : Gender.values()) {

                if (existingGender.choice == choice) {
                    return existingGender;
                }
            }

            return null;
        }
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(final Gender gender) {
        this.gender = gender;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setMobileNumber(final String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setDateOfBirth(final String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String toString() {
        return String.format("%d  Name = %s  Email = %s  Gender = %s  Date Of Birth= %s", id, name, email, gender, dateOfBirth);
    }

}



