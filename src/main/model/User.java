package main.model;

import java.util.UUID;

public class User {
    private UUID userID;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private int roleID;

    public User(UUID userID, String email, String password, String firstName, String lastName, String phoneNumber, int roleID) {
        this.userID = userID;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.roleID = roleID;
    }

    public UUID getUserID() {
        return userID;
    }

    public void setUserID(UUID userID) {
        this.userID = userID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        if (roleID == 1 || roleID == 2) {
            this.roleID = roleID;
        }
    }
}