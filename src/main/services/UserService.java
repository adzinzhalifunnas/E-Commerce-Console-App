package main.services;

import main.helper.UserHelper;
import main.model.request.UserRequestDTO;
import main.util.Password;
import main.util.Util;

public class UserService {
    public static void addUser(int roleID) {
        String email, password, confirmPassword, firstName, lastName, phoneNumber;
        String object = roleID == 1 ? "customer" : "store";
        do {
            System.out.print("Enter email [x@x.x]: ");
            email = Util.scanEmail();
        } while (email == null);
        do {
            System.out.print("Enter password [5 characters minimum]: ");
            password = Util.scanPassword();
        } while (password == null);
        do {
            System.out.print("Enter confirm password [Must match password]: ");
            confirmPassword = Util.scanPassword();
            if (!password.equals(confirmPassword)) {
                System.out.println("[Error] Passwords do not match.");
            }
        } while (!password.equals(confirmPassword));
        do {
            System.out.printf("Enter %s first name [2 characters minimum]: ", object);
            firstName = Util.scanName();
        } while (firstName == null);
        do {
            System.out.printf("Enter %s last name [2 characters minimum]: ", object);
            lastName = Util.scanName();
        } while (lastName == null);
        do {
            System.out.print("Enter phone number [10-13 digits]: ");
            phoneNumber = Util.scanPhoneNumber();
        } while (phoneNumber == null);
        UserRequestDTO userRequestDTO = new UserRequestDTO(null, email, Password.hash(password), firstName, lastName, phoneNumber, 0.00, roleID);
        UserHelper.addUser(userRequestDTO);
    }

    public static Boolean loginUser(int roleID) {
        String email, password;
        do {
            System.out.print("Enter email [x@x.x]: ");
            email = Util.scanEmail();
        } while (email == null);
        do {
            System.out.print("Enter password [5 characters minimum]: ");
            password = Util.scanPassword();
        } while (password == null);
        return UserHelper.loginUser(email, password, roleID);
    }
}
