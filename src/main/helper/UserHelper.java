package main.helper;

import java.util.UUID;

import main.database.Database;
import main.model.Address;
import main.model.User;
import main.model.request.UserRequestDTO;
import main.util.FileManagement;
import main.util.Password;
import main.util.Util;

public class UserHelper {
    public static Boolean isUserExist(String email) {
        for (User user : Database.users) {
            if (user.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    public static void addUser(UserRequestDTO userRequestDTO) {
        Boolean isUserExist = isUserExist(userRequestDTO.getEmail());
        if (!isUserExist) {
            User user = new User(
                userRequestDTO.getUserID() == null ? UUID.randomUUID() : userRequestDTO.getUserID(),
                userRequestDTO.getEmail(),
                userRequestDTO.getPassword(),
                userRequestDTO.getFirstName(),
                userRequestDTO.getLastName(),
                userRequestDTO.getPhoneNumber(),
                userRequestDTO.getRoleID()
            );
            Database.users.add(user);
            FileManagement.writeToFile("users.csv", "users", user);
        }
    }

    public static Boolean loginUser(String email, String password, int roleID) {
        for (User user : Database.users) {
            Boolean isPasswordMatched = Password.verify(password, user.getPassword());
            if (user.getEmail().equals(email) && isPasswordMatched && user.getRoleID() == roleID) {
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

    public static Boolean checkUserAddress(UUID userID) {
        for (Address address : Database.addresses) {
            if (address.getUserID().equals(userID)) {
                return true;
            }
        }
        return false;
    }

    public static void viewUserSettings() {
        int roleID = Database.loggedInUser.getRoleID();
        String object = roleID == 1 ? "customer" : "store";
        System.out.printf("- %sID: %s\n", Util.capitalize(object), Database.loggedInUser.getUserID());
        System.out.printf("- Email: %s\n", Database.loggedInUser.getEmail());
        System.out.printf("- First Name: %s\n", Database.loggedInUser.getFirstName());
        System.out.printf("- Last Name: %s\n", Database.loggedInUser.getLastName());
        System.out.printf("- Phone Number: %s\n", Database.loggedInUser.getPhoneNumber());
        System.out.printf("- Role ID: %d - %s\n", Database.loggedInUser.getRoleID(), (Database.loggedInUser.getRoleID() == 1 ? "Customer" : "Seller"));
        System.out.println("- Addressess:");
        AddressHelper.viewUserAddresses(Database.loggedInUser.getUserID());
    }

    public static void viewAllUsers() {
        int no = 0;
        for (User user : Database.users) {
            System.out.printf("%d. User ID: %s\n", ++no, user.getUserID());
            System.out.printf("   Email: %s\n", user.getEmail());
            System.out.printf("   First Name: %s\n", user.getFirstName());
            System.out.printf("   Last Name: %s\n", user.getLastName());
            System.out.printf("   Phone Number: %s\n", user.getPhoneNumber());
            System.out.printf("   Role ID: %d - %s\n", user.getRoleID(), (user.getRoleID() == 1 ? "Customer" : "Seller"));
            System.out.printf("   Addressess:\n");
            AddressHelper.viewUserAddresses(user.getUserID());
            System.out.println();
        }
    }

    public static void viewAllCustomers() {
        int no = 0;
        for (User user : Database.users) {
            if (user.getRoleID() == 1) {
                System.out.printf("%d. User ID: %s\n", ++no, user.getUserID());
                System.out.printf("   Email: %s\n", user.getEmail());
                System.out.printf("   First Name: %s\n", user.getFirstName());
                System.out.printf("   Last Name: %s\n", user.getLastName());
                System.out.printf("   Phone Number: %s\n", user.getPhoneNumber());
                System.out.printf("   Role ID: %d - Customer\n", user.getRoleID());
                System.out.printf("   Addressess:\n");
                AddressHelper.viewUserAddresses(user.getUserID());
                System.out.println();
            }
        }
    }
    
    public static void viewAllSellers() {
        int no = 0;
        for (User user : Database.users) {
            if (user.getRoleID() == 2) {
                System.out.printf("%d. User ID: %s\n", ++no, user.getUserID());
                System.out.printf("   Email: %s\n", user.getEmail());
                System.out.printf("   First Name: %s\n", user.getFirstName());
                System.out.printf("   Last Name: %s\n", user.getLastName());
                System.out.printf("   Phone Number: %s\n", user.getPhoneNumber());
                System.out.printf("   Role ID: %d - Seller\n", user.getRoleID());
                System.out.printf("   Addressess:\n");
                AddressHelper.viewUserAddresses(user.getUserID());
                System.out.println();
            }
        }
    }
}
