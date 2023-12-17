package main.helper;

import java.util.UUID;

import main.database.Database;
import main.model.User;
import main.model.request.UserRequestDTO;
import main.util.Password;

public class UserHelper {
    public static void addUser(UserRequestDTO userRequestDTO) {
        User user = new User(
            UUID.randomUUID(),
            userRequestDTO.getEmail(),
            Password.hash(userRequestDTO.getPassword()),
            userRequestDTO.getFirstName(),
            userRequestDTO.getLastName(),
            userRequestDTO.getPhoneNumber(),
            userRequestDTO.getRoleID()
        );
        Database.users.add(user);
    }

    public static Boolean loginUser(String email, String password, int roleID) {
        for (User user : Database.users) {
            if (user.getEmail().equals(email) && Password.verify(password, user.getPassword()) && user.getRoleID() == roleID) {
                Database.isLogin = true;
                Database.loggedInUser = user;
                return true;
            }
        }
        return false;
    }

    public static void logoutUser() {
        Database.isLogin = false;
        Database.loggedInUser = null;
    }

    public static void viewAllUsers() {
        int no = 1;
        for (User user : Database.users) {
            System.out.println(no + ". User ID: " + user.getUserID());
            System.out.println("   Email: " + user.getEmail());
            System.out.println("   First Name: " + user.getFirstName());
            System.out.println("   Last Name: " + user.getLastName());
            System.out.println("   Phone Number: " + user.getPhoneNumber());
            System.out.println("   Role ID: " + user.getRoleID() + " - " + (user.getRoleID() == 1 ? "Customer" : "Seller"));
            System.out.println();
            no++;
        }
    }
}
