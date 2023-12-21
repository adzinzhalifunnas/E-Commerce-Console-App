package main.model;

import java.util.UUID;

public class User {
    private UUID userID;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Double balance;
    // its not safe and not recommended to store balance with this data type (like int, double, etc).
    // i recommend you to use transaction table to store the balance history (credit, debit, etc) and calculate the balance from there.
    // but for this project, i think its okay to use this data type because its just a simple project. :)
    private int roleID;

    public User(UUID userID, String email, String password, String firstName, String lastName, String phoneNumber, Double balance, int roleID) {
        this.userID = userID;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.balance = balance;
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

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        if (balance >= 0) {
            this.balance = balance;
        }
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